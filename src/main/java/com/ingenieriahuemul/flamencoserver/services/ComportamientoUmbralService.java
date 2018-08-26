package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.ComportamientoUmbralDao;
import com.ingenieriahuemul.flamencoserver.dao.SensorDao;
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
		Sensor sensor = sensorDao.findById(cUmbral.getId());
		//validaciones
		//enviar mensaje de configuracion al MAS
		
		//si se configura ok se continua sino se notifica el error
		cUmbralDao.save(cUmbral);
		Monitor.setOutdated(true);
	}
	
	public void modificarComportamientoUmbral(ComportamientoUmbral cUmbral) {
		Sensor sensor = sensorDao.findById(cUmbral.getId());
		//validaciones
		//enviar mensaje de configuracion al MAS
		
		//si se configura ok se continua sino se notifica el error
		cUmbralDao.update(cUmbral);
		Monitor.setOutdated(true);
	}
	
	public void eliminarComportamientoUmbral(Long id) {
		Sensor sensor = sensorDao.findById(
				cUmbralDao.findById(id).getId());
		
		//validaciones
		//enviar mensaje de configuracion al MAS
		
		//si se configura ok se continua sino se notifica el error
		
		cUmbralDao.delete(id);
		Monitor.setOutdated(true);
	}
}
