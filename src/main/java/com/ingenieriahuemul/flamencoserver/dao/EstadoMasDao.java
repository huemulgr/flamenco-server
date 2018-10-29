package com.ingenieriahuemul.flamencoserver.dao;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.internal.util.privilegedactions.NewSchema;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.Utilitarios;
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
	
	
	private static final String PROC_ATRASADOS_1 = "flaProcesarAtrasadosPaso1";
	public void procesarAtrasadosPaso1() {
		Map<String, Object> in = new HashMap<String, Object>();
		
		super.ejecutarStoredProcedure(PROC_ATRASADOS_1, in, null, null);
	}	
	
	private static final String PROC_ATRASADOS_2 = "flaProcesarAtrasadosPaso2";
	private static final String P_DATETIME = "fechaHora";
	private static final String P_VALOR2 = "valor";
	private static final String P_ID_PS = "idPuntoSensado";	
//	IN fechaHora DATETIME,
//	IN valor decimal(6,2),
//	IN idPuntoSensado INT 
	
	public void procesarAtrasadosPaso2(Date fechaHora, Double valor, Long idPs) {
		Map<String, Object> in = new HashMap<String, Object>();
		
		in.put(P_DATETIME, new java.sql.Timestamp(fechaHora.getTime()));
		in.put(P_VALOR2, valor);
		in.put(P_ID_PS, idPs);
		
		super.ejecutarStoredProcedure(PROC_ATRASADOS_2, in, null, null);
	}
	
	private static final String PROC_ATRASADOS_3 = "flaProcesarAtrasadosPaso3";
	private static final String P_ID_EMPRESA = "PIDempresa";
	private static final String P_FECHA_INI = "PFechaInicial";
	private static final String P_FECHA_FIN = "PFechaFinal";
//	PIDempresa mediumint unsigned,
//  PFechaInicial	date,
//	PFechaFinal		date
	
	public void procesarAtrasadosPaso3(Long idEmpresa, Date fechaIni, Date fechaFin) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, idEmpresa);
		in.put(P_FECHA_INI, new java.sql.Date(fechaIni.getTime()));
		in.put(P_FECHA_FIN, new java.sql.Date(fechaFin.getTime()));
		
		super.ejecutarStoredProcedure(PROC_ATRASADOS_3, in, null, null);
	}
	
}
