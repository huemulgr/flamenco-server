package com.ingenieriahuemul.flamencoserver.services;

import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Utilitarios;
import com.ingenieriahuemul.flamencoserver.constants.MensajesMesh;
import com.ingenieriahuemul.flamencoserver.dao.AlarmaDao;
import com.ingenieriahuemul.flamencoserver.dao.PuntoDeSensadoDao;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.domain.EstadoMas;
import com.ingenieriahuemul.flamencoserver.domain.Mas;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

/** servicio responsable de mantener listado de mas y regular acceso al mesh */
@Service
public class MasService {
	private final Logger logger = Logger.getLogger(MasService.class);
	
	@Autowired
	private SensorDao sensorDao;
	@Autowired
	private AlarmaDao alarmaDao;
	@Autowired
	private PuntoDeSensadoDao puntoDeSensadoDao;
	@Autowired
    private EstadoMasService estadoMasService;

    private List<EstadoMas> estadoActual;
    
    //lista unica para el servicio, uso un map para facilitar el acceso por un id de sensor
	private static HashMap<Long, Mas> listadoMas = new HashMap();
	
	//semaforo para asegurar que hay un unico mensaje en transito a la vez
	private static Semaphore semaforoMesh = new Semaphore(1, true);
	
	/* metodo para traer los sensores con sus alarmas
	 * 
	 * */
	//TODO: es posible hacer esto unicamente para el que cambio no para todos
	public void refrescarListaMAS() {
		listadoMas = new HashMap();
		List<Sensor> sensores = sensorDao.findAll();
		for(Sensor sensor : sensores) {
			Mas mas = new Mas();
			
			//si no esta asignado viene un string
			try {
				mas.setPuntoDeSensado(puntoDeSensadoDao.findById(Long.valueOf(sensor.getIdPuntoSensado())));
			} catch (NumberFormatException e) {
				//en ese caso se ignora el sensor, ya que no esta asignado a ningun punto de sensado
				continue;
			}
			

			if(!sensor.getHabilitado() || !mas.getPuntoDeSensado().getHabilitado()) {
				continue;
			}
			
			mas.setSensor(sensor);
			if(sensor.getTieneAlarma()) {
				mas.setAlarmas(alarmaDao.findByIdSensor(sensor.getId()));
			}
			
			//TODO: se supone que el garbage collector se ocupa de cerrar sockets perdidos, pero estaria bueno hacerlo explicito por las dudas, 
			//ademas de no crear nuevo socket para cada cambio, sino solo para el cambio de coordinador
			mas.abrirConexionConMesh();
			
			listadoMas.put(mas.getPuntoDeSensado().getId(), mas);
		}
	}
	
	public void refrescarEstadoActual() {
		for(Mas mas: listadoMas.values()) {
			String status = obtenerStatus(mas);
			
			logger.info("status recibido: " + status);
		} 
		
		estadoActual = estadoMasService.obtenerEstadoActual();
		
		for(EstadoMas estadoMas : estadoActual) {
			for(Mas mas : listadoMas.values()) {
				if(mas.getPuntoDeSensado() != null && 
						estadoMas.getIdPuntoSensado().equals(mas.getPuntoDeSensado().getId())) {
					mas.setEstadoMas(estadoMas);
				}
			}
		}
	}
	
	public String obtenerStatus(Mas mas) {
		Map<String, Object> datosObtenidos = null;
		String respuesta=null;
		try {
			semaforoMesh.acquire();
				datosObtenidos = mas.comunicarseConMesh(mas.getSensor().getMac() +
					String.format(MensajesMesh.MESH_STATUS, 
						Utilitarios.formatofechaStatus(new Date())
					)
				);
			semaforoMesh.release();
			
			EstadoMas estado = (EstadoMas)datosObtenidos.get("estado");
			estadoMasService.actualizarEstadoMas(mas.getSensor().getMac(), estado);
			
			respuesta = (String)datosObtenidos.get("respuesta");
		} catch (InterruptedException e) {
			logger.error("Error inesperado en el semaforo de la red mesh: ", e);
		}
		return respuesta;
	}
	
	public void evaluarAlarmas() {
		for(Mas mas : listadoMas.values()) {
    		mas.evaluarAlarmas();   			
    	}
	}
	
}
