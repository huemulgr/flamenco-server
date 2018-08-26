package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.AlarmaDao;
import com.ingenieriahuemul.flamencoserver.domain.Alarma;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Service
public class AlarmaService {
	@Autowired
	private AlarmaDao alarmaDao;
	
	public List<Alarma> obtenerAlarmas() {
		return alarmaDao.findAll();
	}
	
	public List<Alarma> obtenerAlarmasSensor(Long idSensor) {
		return alarmaDao.findByIdSensor(idSensor);
	}
	
	public Alarma obtenerAlarma(Long id) {
		return alarmaDao.findById(id);
	}
	
	public void crearAlarma(Alarma alarma) {
		alarmaDao.save(alarma);
		Monitor.setOutdated(true);
	}
	
	public void modificarAlarma(Alarma alarma) {
		alarmaDao.update(alarma);
		Monitor.setOutdated(true);
	}
	
	public void eliminarAlarma(Long id) {
		alarmaDao.delete(id);
		Monitor.setOutdated(true);
	}
}
