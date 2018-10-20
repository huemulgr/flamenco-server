package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.ComportamientoUmbralDao;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
import com.ingenieriahuemul.flamencoserver.domain.ComportamientoHora;
import com.ingenieriahuemul.flamencoserver.domain.ComportamientoUmbral;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Service
public class ComportamientoUmbralService {
	@Autowired
	private ComportamientoUmbralDao cUmbralDao;
	@Autowired
	private SensorDao sensorDao;
	
	public List<ComportamientoUmbral> obtenerComportamientoUmbrals() {
		return cUmbralDao.findAll();
	}
	
	public List<ComportamientoUmbral> obtenerComportamientoUmbralsSensor(Long idSensor) {
		return cUmbralDao.findByIdSensor(idSensor);
	}
	
	public ComportamientoUmbral obtenerComportamientoUmbral(Long id) {
		return cUmbralDao.findById(id);
	}
	
	public void crearComportamientoUmbral(ComportamientoUmbral cUmbral) {
		cUmbralDao.save(cUmbral);
	}
	
	public void modificarComportamientoUmbral(ComportamientoUmbral cUmbral) {
		cUmbralDao.update(cUmbral);
	}
	
	public void eliminarComportamientoUmbral(Long id) {
		cUmbralDao.delete(id);
	}
	
	public void deshabilitarCompUmbral(Long idCompUmbral) {
		if(idCompUmbral != null) {
			ComportamientoUmbral comportamientoUmbral = obtenerComportamientoUmbral(idCompUmbral);
			if(comportamientoUmbral != null) {
				comportamientoUmbral.setHabilitado(false);
				modificarComportamientoUmbral(comportamientoUmbral);
			}
		}
	}
}
