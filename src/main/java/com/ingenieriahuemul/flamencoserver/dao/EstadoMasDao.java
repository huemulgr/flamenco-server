package com.ingenieriahuemul.flamencoserver.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.EstadoMas;

@Component
public class EstadoMasDao extends BaseDao{

	public List<EstadoMas> obtenerEstadoActual() {
		
		String sql = "SELECT * FROM ESTADOACTUAL";
		//cambiar x sp
		List<EstadoMas> estadoActual = 
			this.jdbcTemplate.query(sql, 
			   new BeanPropertyRowMapper(EstadoMas.class));
		
		return estadoActual;
	}
	
}
