package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dao.AlarmaDao;
import com.ingenieriahuemul.flamencoserver.dao.EstadoMasDao;
import com.ingenieriahuemul.flamencoserver.domain.Alarma;
import com.ingenieriahuemul.flamencoserver.domain.EstadoMas;

@Service
public class EstadoMasService {
	@Autowired
	private EstadoMasDao estadoMasDao;
	
	@Autowired
	private AlarmaDao alarmaDao;
	
	public List<EstadoMas> obtenerEstadoActual() {
		return estadoMasDao.obtenerEstadoActual();
	}
	
	public void actualizarEstadoMas(String mac, EstadoMas estado) {
		estadoMasDao.guardarEstado(mac, estado);
	}
	
}
