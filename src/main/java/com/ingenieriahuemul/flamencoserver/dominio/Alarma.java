package com.ingenieriahuemul.flamencoserver.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class Alarma {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
}
