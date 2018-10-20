package com.ingenieriahuemul.flamencoserver.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Utilitarios;
import com.ingenieriahuemul.flamencoserver.constants.MensajesMesh;
import com.ingenieriahuemul.flamencoserver.dao.AlarmaDao;
import com.ingenieriahuemul.flamencoserver.dao.PuntoDeSensadoDao;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.domain.ComportamientoHora;
import com.ingenieriahuemul.flamencoserver.domain.ComportamientoUmbral;
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
    
    //lista unica para el servicio, uso un map para facilitar el acceso por un id de punto de sensado
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
			
//			logger.info("status recibido: " + status);
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
			logger.info("------------------------------Semaforo ABIERTO");
				datosObtenidos = mas.comunicarseConMesh(mas.getSensor().getMac() +
					String.format(
						(mas.isEnRecuperacion() ? MensajesMesh.MESH_STATUS : MensajesMesh.MESH_STATUS_O), 
						Utilitarios.formatoFechaStatus(new Date())
					)
				);
			} catch (Exception e) {
				logger.error("Error inesperado en el semaforo de la red mesh: ", e);
			} finally {
				logger.info("------------------------------Semaforo CERRADO");	
				semaforoMesh.release();
			}
			
			
			EstadoMas estado = (EstadoMas)datosObtenidos.get("estado");
			if(estado != null) {
				estadoMasService.actualizarEstadoMas(mas.getSensor().getMac(), estado);
			}
			
			respuesta = (String)datosObtenidos.get("respuesta");
		
		return respuesta;
	}
	
	/** activa manualmente un rele, al hacerlo se pierde la configuracion previa del mismo
	 * @return	true o false segun si pudo activar o no */
	public boolean activarReleManual(Long idPuntoSensado, int nroRele) {
		return activacionManual(true, idPuntoSensado, nroRele);
	}
	
	/** desactiva manualmente un rele, al hacerlo se pierde la configuracion previa del mismo
	 * @return	true o false segun si pudo desactivar o no */
	public boolean desactivarReleManual(Long idPuntoSensado, int nroRele) {
		return activacionManual(false, idPuntoSensado, nroRele);
	}
	
	private boolean activacionManual(boolean activar, Long idPuntoSensado, int nroRele) {
		Map<String, Object> datosObtenidos = null;
		String respuesta="";
		Mas mas = this.listadoMas.get(idPuntoSensado);
		try {
			semaforoMesh.acquire();
			logger.info("------------------------------Semaforo ABIERTO");
			datosObtenidos = mas.comunicarseConMesh(mas.getSensor().getMac() +
				String.format(
						(activar ? MensajesMesh.MESH_C_MANUAL_ON : MensajesMesh.MESH_C_MANUAL_OFF),
						nroRele)
			);
		} catch (Exception e) {
			logger.error("Error inesperado en el semaforo de la red mesh: ", e);
		} finally {
			logger.info("------------------------------Semaforo CERRADO");	
			semaforoMesh.release();			
		}

			EstadoMas estado = (EstadoMas)datosObtenidos.get("estado");
			if(estado != null) {
				estadoMasService.actualizarEstadoMas(mas.getSensor().getMac(), estado);
			} else {
				return false;
			}
			
			respuesta = (String)datosObtenidos.get("respuesta");
		
		return true;
	}
	
	public boolean configurarReleXUmbral(ComportamientoUmbral cUmbral) {
		//vemos si el sensor esta asignado y de paso obtenemos el id de ps
		Long idPs = buscarIdPuntoSensadoXIdSensor(cUmbral.getIdSensor());
		if(idPs < 0)
			return false;
		
		Map<String, Object> datosObtenidos = null;
		String respuesta="";
		Mas mas = this.listadoMas.get(idPs);
		try {
			semaforoMesh.acquire();
			logger.info("------------------------------Semaforo ABIERTO");
			datosObtenidos = mas.comunicarseConMesh(mas.getSensor().getMac() +
				String.format(MensajesMesh.MESH_C_UMBRAL, 
						cUmbral.getNroContactorSalida(),
						cUmbral.getUmbralInf(),
						cUmbral.getUmbralSup(),
						cUmbral.getHabilitarContEntrada() ? (cUmbral.getCondicionY() ? "Y" : "O") : "N",
						cUmbral.getHabilitarContEntrada() ? (cUmbral.getContactorEntrada() ? "1" : "0") : "N"
					)
			);
		} catch (Exception e) {
			logger.error("Error inesperado en el semaforo de la red mesh: ", e);
		} finally {
			semaforoMesh.release();
			logger.info("------------------------------Semaforo CERRADO");			
		}

		EstadoMas estado = (EstadoMas)datosObtenidos.get("estado");
		if(estado != null) {
			estadoMasService.actualizarEstadoMas(mas.getSensor().getMac(), estado);
		} else {
			return false;
		}
			
		respuesta = (String)datosObtenidos.get("respuesta");
//		logger.info(respuesta);
		
		return true;
	}
	
	public boolean configurarReleXHora(ComportamientoHora cHora) {
		//vemos si el sensor esta asignado y de paso obtenemos el id de ps
		Long idPs = buscarIdPuntoSensadoXIdSensor(cHora.getIdSensor());
		if(idPs < 0)
			return false;
		
		Map<String, Object> datosObtenidos = null;
		String respuesta="";
		Mas mas = this.listadoMas.get(idPs);
		try {
			semaforoMesh.acquire();
			logger.info("------------------------------Semaforo ABIERTO");
			datosObtenidos = mas.comunicarseConMesh(mas.getSensor().getMac() +
				String.format(MensajesMesh.MESH_C_HORA, 
						cHora.getNroContactorSalida(),
						Utilitarios.formatoHora(cHora.getHoraInicio()),
						Utilitarios.formatoHora(cHora.getHoraFin()),
						cHora.getHabilitarContEntrada() ? (cHora.getCondicionY() ? "Y" : "O") : "N",
						cHora.getHabilitarContEntrada() ? (cHora.getContactorEntrada() ? "1" : "0") : "N"
					)
			);
		} catch (Exception e) {
			logger.error("Error inesperado en el semaforo de la red mesh: ", e);
//			throw e;
			return false;
		} finally {
			logger.info("------------------------------Semaforo CERRADO");
			semaforoMesh.release();			
		}

		EstadoMas estado = (EstadoMas)datosObtenidos.get("estado");
		if(estado != null) {
			estadoMasService.actualizarEstadoMas(mas.getSensor().getMac(), estado);
		} else {
			return false;
		}
			
		respuesta = (String)datosObtenidos.get("respuesta");
		
		return true;
	}
	
	public void evaluarAlarmas() {
		for(Mas mas : listadoMas.values()) {
    		mas.evaluarAlarmas();   			
    	}
	}
	
	/** encuentra si en el listado mas esta el idSensor de entrada, devuelve menos 1 en el caso que no exista como se da cuando no esta asignado el sensor */
	private Long buscarIdPuntoSensadoXIdSensor(Long idSensor) {
		for(Entry<Long, Mas> entry : this.listadoMas.entrySet()) {
			if(entry.getValue().getIdSensor() == idSensor) {
				return entry.getKey();
			}
		}
		return -1L;
	}
	
}
