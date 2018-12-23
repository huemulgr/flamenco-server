package com.ingenieriahuemul.flamencoserver.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
	


	/* devuelvo vista pivot como lista de object, no tengo forma de saber a priori cuantas columnas tiene 
	 * (en realidad si pero se traduciria en una clase creada en tiempo de ejecucion etc. se complica) */
	private static final String PIVOT = "flaGenerarVistaPivot";
	private static final String P_PAGINA = "pPagina";
	private static final String P_MOSTRAR = "pMostrarHabilitados";
	public List vistaPivot(Date fecha) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_FECHA_DESDE, fecha);
		in.put(P_FECHA_HASTA, fecha);
		in.put(P_PAGINA, 1);
		in.put(P_MOSTRAR, true);
		
		
		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
    	call.withProcedureName(PIVOT);    	
    	
//    	if(clase != null) {			//validacion para utilizar el metodo cuando no se devuelven filas (abm u otros)
//    		call.returningResultSet(clase.toString(), new BeanPropertyRowMapper(clase));
//    	}
    	
    	Map<String, Object> out;
    	out = call.execute(in);    	
    	
    	Map<String, Object> outParams = new HashMap<String, Object>();
    	outParams.putAll(out);
    	
    	List<Object> registrosOrdenados = new ArrayList();
    	
    	List<Map<String, Object>> registros = (List<Map<String, Object>>) outParams.get("#result-set-1");
    	for(Map<String, Object> registro : registros) {
    		registrosOrdenados.add(new ArrayList(registro.entrySet()));
    	}
    	
    	return registrosOrdenados;
	}	
	
}
