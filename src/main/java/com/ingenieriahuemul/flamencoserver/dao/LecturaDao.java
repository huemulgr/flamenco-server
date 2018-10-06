package com.ingenieriahuemul.flamencoserver.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.Lectura;

@Component
public class LecturaDao extends BaseDao{
	
	//parametros
	private static final String P_ID_PUNTO_DE_SENSADO = "pidPuntoSensado";
	private static final String P_FECHA_DESDE = "pfechaDesde";
	private static final String P_FECHA_HASTA = "pfechaHasta";
	
	//stored procedures
	private static final String CONSULTA = "flaInformeLecturas";
	
//	pidPuntoSensado  int unsigned,
//  pfechaDesde date, 
//  pfechaHasta date
	public List<Lectura> obtenerLecturas(Long idPuntoSensado, Date fechaDesde, Date fechaHasta) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PUNTO_DE_SENSADO, idPuntoSensado);
		in.put(P_FECHA_DESDE, fechaDesde);
		in.put(P_FECHA_HASTA, fechaHasta);
		return (List<Lectura>) super.ejecutarStoredProcedure(CONSULTA, in, null, Lectura.class);
	}
	
}
