package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dao.AlarmaDao;
import com.ingenieriahuemul.flamencoserver.dao.ComportamientoHoraDao;
import com.ingenieriahuemul.flamencoserver.dao.ComportamientoUmbralDao;
import com.ingenieriahuemul.flamencoserver.dao.PuntoDeSensadoDao;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.dao.TipoSensorDao;
import com.ingenieriahuemul.flamencoserver.dominio.Sensor;

@Service
public class SensorService {
	@Autowired
	private SensorDao sensorDao;
	@Autowired
	private AlarmaDao alarmaDao;
	@Autowired
	private ComportamientoHoraDao comportamientoHoraDao;
	@Autowired
	private ComportamientoUmbralDao comportamientoUmbralDao;
	@Autowired
	private TipoSensorDao tipoSensorDao;
	@Autowired
	private PuntoDeSensadoDao puntoDeSensadoDao;
	
	/* metodo para traer los sensores con todas las entidades asociadas requeridas para monitorear
	 * 
	 * */
	public List<Sensor> getListaMAS() {
		List<Sensor> sensores = sensorDao.findAll();
		for(Sensor sensor : sensores) {
			if(sensor.getTieneAlarma()) {
				sensor.setListaAlarmas(alarmaDao.findByIdSensor(sensor.getId()));
			}
			if(sensor.getTieneCompHora()) {
				sensor.setListaCompHora(comportamientoHoraDao.findByIdSensor(sensor.getId()));
			}
			if(sensor.getTieneCompUmbral()) {
				sensor.setListaCompUmbral(comportamientoUmbralDao.findByIdSensor(sensor.getId()));;
			}
			//TODO: esto es feo, ver posibilidad de pasar id directo en el select
			if (!"No asignado".equals(sensor.getPuntoSensado())) {
				sensor.setPuntoDeSensadoAsignado(puntoDeSensadoDao.findById(Long.valueOf(sensor.getPuntoSensado())));
			}
		}
		return sensores;
	}
}
