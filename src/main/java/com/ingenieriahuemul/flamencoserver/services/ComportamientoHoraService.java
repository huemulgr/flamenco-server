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
		cHoraDao.save(cHora);
	}
	
	public void modificarComportamientoHora(ComportamientoHora cHora) {
		cHoraDao.update(cHora);
	}
	
	public void eliminarComportamientoHora(Long id) {
		cHoraDao.delete(id);
	}
	
	public void deshabilitarCompHora(Long idCompHora) {
		if(idCompHora != null) {
			ComportamientoHora comportamientoHora = obtenerComportamientoHora(idCompHora);
			if(comportamientoHora != null) {
				comportamientoHora.setHabilitado(false);
				modificarComportamientoHora(comportamientoHora);
			}
		}
	}
}
