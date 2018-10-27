package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Service
public class SensorService {
	private final Logger logger = Logger.getLogger(SensorService.class);
	
	@Autowired
	private SensorDao sensorDao;
	
	public List<Sensor> findAll() {
		return sensorDao.findAll();
	}
	
	public Sensor findById(Long id) {
		return sensorDao.findById(id);
	}
	
	public void create(Sensor sensor) {
		logger.info("Creando sensor...");
		sensorDao.save(sensor);
		Monitor.setOutdated(true);
	}
	
	public void update(Sensor sensor) {
		logger.info("Actualizando sensor...");
		sensorDao.update(sensor);
		Monitor.setOutdated(true);
	}
	
	public void delete(Long id) {
		logger.info("Eliminando sensor...");
		sensorDao.delete(id);
		Monitor.setOutdated(true);
	}
}
