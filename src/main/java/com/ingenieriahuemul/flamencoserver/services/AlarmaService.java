package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.AlarmaDao;
import com.ingenieriahuemul.flamencoserver.domain.Alarma;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Service
public class AlarmaService {
	private final Logger logger = Logger.getLogger(AlarmaService.class);
	
	@Autowired
	private AlarmaDao alarmaDao;
	
	public List<Alarma> findAll() {
		return alarmaDao.findAll();
	}
	
	public List<Alarma> findByIdSensor(Long idSensor) {
		return alarmaDao.findByIdSensor(idSensor);
	}
	
	public Alarma findById(Long id) {
		return alarmaDao.findById(id);
	}
	
	public void create(Alarma alarma) {
		logger.info("Creando alarma...");
		alarmaDao.save(alarma);
		Monitor.setOutdated(true);
	}
	
	public void update(Alarma alarma) {
		logger.info("Actualizando alarma...");
		alarmaDao.update(alarma);
		Monitor.setOutdated(true);
	}
	
	public void delete(Long id) {
		logger.info("Eliminando alarma...");
		alarmaDao.delete(id);
		Monitor.setOutdated(true);
	}
}
