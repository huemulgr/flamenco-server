package com.ingenieriahuemul.flamencoserver.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dao.EstadoMasDao;
import com.ingenieriahuemul.flamencoserver.domain.EstadoMas;

/** servicio para el manejo del estado actual de los mas */
@Service
public class EstadoMasService {
	@Autowired
	private EstadoMasDao estadoMasDao;
	
	
	/** obtiene el status de todos los mas de la tabla en memoria */
	public List<EstadoMas> obtenerEstadoActual() {
		return estadoMasDao.obtenerEstadoActual();
	}
	
	/** guarda estado actual de un mas a tabla en memoria */
	public void actualizarEstadoMas(String mac, EstadoMas estado) {
		estadoMasDao.guardarEstado(mac, estado);
	}
	
	/** vaciar mediciones temporales */
	public void procesarAtrasadosPaso1() {
		estadoMasDao.procesarAtrasadosPaso1();
	}
	
	/** guardar mediciones temporales */
	public void procesarAtrasadosPaso2(Date fechaHora, Double valor, Long idPs) {
		estadoMasDao.procesarAtrasadosPaso2(fechaHora, valor, idPs);
	}
	
	/** guardar mediciones finales */
	public void procesarAtrasadosPaso3(Long idEmpresa, Date fechaIni, Date fechaFin) {
		estadoMasDao.procesarAtrasadosPaso3(idEmpresa, fechaIni, fechaFin);
	}
	
}
