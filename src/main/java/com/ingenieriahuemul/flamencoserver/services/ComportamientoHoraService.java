package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.ComportamientoHoraDao;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.domain.ComportamientoHora;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Service
public class ComportamientoHoraService {
	@Autowired
	private ComportamientoHoraDao cHoraDao;
	@Autowired
	private SensorDao sensorDao;
	
	public List<ComportamientoHora> obtenerComportamientoHoras() {
		return cHoraDao.findAll();
	}
	
	public List<ComportamientoHora> obtenerComportamientoHorasSensor(Long idSensor) {
		return cHoraDao.findByIdSensor(idSensor);
	}
	
	public ComportamientoHora obtenerComportamientoHora(Long id) {
		return cHoraDao.findById(id);
	}
	
	public void crearComportamientoHora(ComportamientoHora cHora) {
		Sensor sensor = sensorDao.findById(cHora.getId());
		//validaciones
		//enviar mensaje de configuracion al MAS
		
		//si se configura ok se continua sino se notifica el error
		cHoraDao.save(cHora);
		Monitor.setOutdated(true);
	}
	
	public void modificarComportamientoHora(ComportamientoHora cHora) {
		Sensor sensor = sensorDao.findById(cHora.getId());
		//validaciones
		//enviar mensaje de configuracion al MAS
		
		//si se configura ok se continua sino se notifica el error
		cHoraDao.update(cHora);
		Monitor.setOutdated(true);
	}
	
	public void eliminarComportamientoHora(Long id) {
		Sensor sensor = sensorDao.findById(
				cHoraDao.findById(id).getId());
		
		//validaciones
		//enviar mensaje de configuracion al MAS
		
		//si se configura ok se continua sino se notifica el error
		
		cHoraDao.delete(id);
		Monitor.setOutdated(true);
	}
}
