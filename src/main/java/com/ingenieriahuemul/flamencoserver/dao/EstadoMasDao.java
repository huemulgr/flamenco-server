package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.EstadoMas;
import com.ingenieriahuemul.flamencoserver.domain.Perfil;

@Component
public class EstadoMasDao extends BaseDao{

	private static final String CONSULTA = "flaEstadoActual";
	private static final String GUARDAR = "flaGuardaEstadoSensor";
	
	private static final String P_VALOR = "Pvalor";
	private static final String P_MAC = "Pmac";
	private static final String P_EST_RE1 = "PestadoRE1";
	private static final String P_EST_RE2 = "PestadoRE2";
	private static final String P_EST_RS1 = "PestadoRS1";
	private static final String P_EST_RS2 = "PestadoRS2";
	
	
	public List<EstadoMas> obtenerEstadoActual() {
		
//		String sql = "SELECT * FROM ESTADOACTUAL";
//		//cambiar x sp
//		List<EstadoMas> estadoActual = 
//			this.jdbcTemplate.query(sql, 
//			   new BeanPropertyRowMapper(EstadoMas.class));
//		
//		return estadoActual;
		
		Map<String, Object> in = new HashMap<String, Object>();
		return (List<EstadoMas>) super.ejecutarStoredProcedure(CONSULTA, in, null, EstadoMas.class);
	}
	
	
//	IN 		Pvalor			decimal(6,2)
//	,IN		Pmac			char(16)
//  ,IN		PestadoRE1		bool
//  ,IN		PestadoRE2		bool
//  ,IN		PestadoRS1		bool
//  ,IN		PestadoRS2		bool	
	public void guardarEstado(String mac, EstadoMas estado) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_MAC, mac);
		in.put(P_VALOR, estado.getValor());
		in.put(P_EST_RE1, estado.getEstadoR1E());
		in.put(P_EST_RE2, estado.getEstadoR2E());
		in.put(P_EST_RS1, estado.getEstadoR1S());
		in.put(P_EST_RS2, estado.getEstadoR2S());
		
		super.ejecutarStoredProcedure(GUARDAR, in, null, EstadoMas.class);
	}
	
}
