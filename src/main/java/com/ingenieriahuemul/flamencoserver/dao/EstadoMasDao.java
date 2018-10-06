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
	
}
