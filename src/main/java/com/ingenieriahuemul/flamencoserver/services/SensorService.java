package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Service
public class SensorService {
	@Autowired
	private SensorDao sensorDao;
	
	public List<Sensor> obtenerSensores() {
		return sensorDao.findAll();
	}
	
	public Sensor obtenerSensor(Long id) {
		return sensorDao.findById(id);
	}
	
	public void crearSensor(Sensor sensor) {
		sensorDao.save(sensor);
		Monitor.setOutdated(true);
	}
	
	public void modificarSensor(Sensor sensor) {
		sensorDao.update(sensor);
		Monitor.setOutdated(true);
	}
	
	public void eliminarSensor(Long id) {
		sensorDao.delete(id);
		Monitor.setOutdated(true);
	}
}
