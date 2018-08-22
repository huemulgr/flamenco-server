package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.ComportamientoUmbral;

@Component
public class ComportamientoUmbralDao extends BaseDao{
	
	//parametros
	private static final String P_ID_COMP_UMBRAL = "Pidcsu";
	private static final String P_HABILITADO = "Phabilitado";
	private static final String P_UMBRAL_SUP = "Pumbralsuperior";
	private static final String P_UMBRAL_INF = "Pumbralinferior";
	private static final String P_CONTACTOR_ENTRADA = "Pcontactorentrada";
	private static final String P_CONTACTOR_SALIDA = "Pcontactorsalida";
	private static final String P_ID_SENSOR = "Pidsensor";
	
	
	//stored procedures
	private static final String CONSULTA = "flaCompUmbralSele";
	private static final String CONSULTA_SENSOR = "flaCompUmbralSensorSele";
	private static final String ALTA = "flaCSUAlta";
	private static final String BAJA = "flaCSUBaja";	
	private static final String MODIFICACION = "flaCSUModif";
		
	
	public List<ComportamientoUmbral> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_UMBRAL, 0);
		return (List<ComportamientoUmbral>) super.ejecutarStoredProcedure(CONSULTA, in, null, ComportamientoUmbral.class);
	}
	
//	IN Pidalarma mediumint unsigned	
	public ComportamientoUmbral findById(Long idComportamientoUmbral) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_UMBRAL, idComportamientoUmbral);
		return ((List<ComportamientoUmbral>)super.ejecutarStoredProcedure(CONSULTA, in, null, ComportamientoUmbral.class)).get(0);
	}
	
//	IN Pidalarma mediumint unsigned	
	public List<ComportamientoUmbral> findByIdSensor(Long idSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, idSensor);
		return (List<ComportamientoUmbral>)super.ejecutarStoredProcedure(CONSULTA_SENSOR, in, null, ComportamientoUmbral.class);
	}
	
//	IN Pidcsu mediumint unsigned,
//	IN Phabilitado bit(1),
//	IN Pumbralsuperior decimal(6,2) ,
//	IN Pumbralinferior decimal(6,2),
//	IN Pcontactorentrada mediumint unsigned,
//	IN Pcontactorsalida mediumint unsigned,
//	IN Pidsensor mediumint unsigned
//TODO: idcsu no queda en el objeto con el valor autogenerado x el sp, no debera ser un problema
	public ComportamientoUmbral save(ComportamientoUmbral compUmbral) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_UMBRAL, 0);
		in.put(P_HABILITADO, compUmbral.getHabilitado());
		in.put(P_UMBRAL_SUP, compUmbral.getUmbralSup());
		in.put(P_UMBRAL_INF, compUmbral.getUmbralInf());
		in.put(P_CONTACTOR_ENTRADA, compUmbral.getContactorEntrada());
		in.put(P_CONTACTOR_SALIDA, compUmbral.getContactorSalida());
		in.put(P_ID_SENSOR, compUmbral.getIdSensor());
		
		super.ejecutarStoredProcedure(ALTA, in, null, ComportamientoUmbral.class);
		return compUmbral;
	}
	
//	IN PIdComportamientoUmbral mediumint unsigned 
	public void delete (Long idComportamientoUmbral) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_UMBRAL, idComportamientoUmbral);
		super.ejecutarStoredProcedure(BAJA, in, null, ComportamientoUmbral.class);
	}
	
//	IN Pidcsu mediumint unsigned,
//	IN Phabilitado bit(1),
//	IN Pumbralsuperior decimal(6,2) ,
//	IN Pumbralinferior decimal(6,2),
//	IN Pcontactorentrada mediumint unsigned,
//	IN Pcontactorsalida mediumint unsigned,
//	IN Pidsensor mediumint unsigned
	public ComportamientoUmbral update (ComportamientoUmbral compUmbral) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_UMBRAL, compUmbral.getId());
		in.put(P_HABILITADO, compUmbral.getHabilitado());
		in.put(P_UMBRAL_SUP, compUmbral.getUmbralSup());
		in.put(P_UMBRAL_INF, compUmbral.getUmbralInf());
		in.put(P_CONTACTOR_ENTRADA, compUmbral.getContactorEntrada());
		in.put(P_CONTACTOR_SALIDA, compUmbral.getContactorSalida());
		in.put(P_ID_SENSOR, compUmbral.getIdSensor());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, ComportamientoUmbral.class);
		return compUmbral;
	}
}
