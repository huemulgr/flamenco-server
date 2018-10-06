package com.ingenieriahuemul.flamencoserver.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dao.AlarmaDao;
import com.ingenieriahuemul.flamencoserver.dao.PuntoDeSensadoDao;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.domain.Mas;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Service
public class MasService {
	private final Logger logger = Logger.getLogger(MasService.class);
	
	@Autowired
	private SensorDao sensorDao;
	@Autowired
	private AlarmaDao alarmaDao;
	@Autowired
	private PuntoDeSensadoDao puntoDeSensadoDao;
	
	/* metodo para traer los sensores con sus alarmas
	 * 
	 * */
	public List<Mas> refrescarListaMAS() {
		List<Mas> listaMas = new ArrayList<Mas>();
		List<Sensor> sensores = sensorDao.findAll();
		for(Sensor sensor : sensores) {
			Mas mas = new Mas();
			mas.setSensor(sensor);
			if(sensor.getTieneAlarma()) {
				mas.setAlarmas(alarmaDao.findByIdSensor(sensor.getId()));
			}
			//si no esta asignado viene un string
			if (sensor.getIdPuntoSensado() instanceof Long) {
				mas.setPuntoDeSensado(puntoDeSensadoDao.findById((Long)sensor.getIdPuntoSensado()));
			}
			listaMas.add(mas);
		}
		return listaMas;
	}
}
