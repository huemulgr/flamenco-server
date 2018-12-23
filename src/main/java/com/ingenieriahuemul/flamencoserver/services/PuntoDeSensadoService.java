package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.Monitor;
import com.ingenieriahuemul.flamencoserver.dao.PuntoDeSensadoDao;
import com.ingenieriahuemul.flamencoserver.domain.PuntoDeSensado;

@Service
public class PuntoDeSensadoService {
	private final Logger logger = Logger.getLogger(PuntoDeSensadoService.class);
	
	@Autowired
	private PuntoDeSensadoDao puntoDeSensadoDao;
	
	public List<PuntoDeSensado> findAll() {
		return puntoDeSensadoDao.findAll();
	}
	
	public PuntoDeSensado findById(Long id) {
		return puntoDeSensadoDao.findById(id);
	}
	
	public void create(PuntoDeSensado puntoDeSensado) {
		logger.info("Creando punto de sensado...");
		puntoDeSensadoDao.save(puntoDeSensado);
		Monitor.setOutdated(true);
	}
	
	public void update(PuntoDeSensado puntoDeSensado) {
		logger.info("Actualizando punto de sensado...");
		puntoDeSensadoDao.update(puntoDeSensado);
		Monitor.setOutdated(true);
	}
	
	public void delete(Long id) {
		logger.info("Eliminando punto de sensado...");
		puntoDeSensadoDao.delete(id);
		Monitor.setOutdated(true);
	}
}
