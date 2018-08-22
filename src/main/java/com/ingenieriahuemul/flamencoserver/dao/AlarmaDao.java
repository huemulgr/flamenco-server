package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.Alarma;

@Component
public class AlarmaDao extends BaseDao{
	
	//parametros
	private static final String P_ID_ALARMA = "PidAlarma";
	private static final String P_HABILITADO = "Phabilitado";
	private static final String P_UMBRAL_SUPERIOR = "Pumbralsuperior";
	private static final String P_UMBRAL_INFERIOR = "Pumbralinferior";
	private static final String P_NOMBRE = "Pnombre";
	private static final String P_NOTIFICAR = "Pnotificar";
	private static final String P_ID_SENSOR = "Pidsensor";
	
	//stored procedures
	private static final String CONSULTA = "flaAlarmaSele";
	private static final String CONSULTA_SENSOR = "flaAlarmaSensorSele";
	private static final String ALTA = "flaAlarmaAlta";
	private static final String BAJA = "flaAlarmaBaja";	
	private static final String MODIFICACION = "flaAlarmaModif";
		
	
	public List<Alarma> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_ALARMA, 0);
		return (List<Alarma>) super.ejecutarStoredProcedure(CONSULTA, in, null, Alarma.class);
	}
	
//	IN Pidalarma mediumint unsigned	
	public Alarma findById(Long idAlarma) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_ALARMA, idAlarma);
		return ((List<Alarma>)super.ejecutarStoredProcedure(CONSULTA, in, null, Alarma.class)).get(0);
	}
	
//	IN Pidalarma mediumint unsigned	
	public List<Alarma> findByIdSensor(Long idSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, idSensor);
		return (List<Alarma>)super.ejecutarStoredProcedure(CONSULTA_SENSOR, in, null, Alarma.class);
	}
	
//	INOUT Pidalarma mediumint unsigned,
//	IN Phabilitado bit(1),
//	IN Pumbralsuperior decimal(6,2) ,
//	IN Pumbralinferior decimal(6,2),
//	IN Pnombre varchar(100),
//	IN Pnotificar bit(1),
//	IN Pidsensor mediumint unsigned
	public Alarma save(Alarma alarma) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_ALARMA, alarma.getId());
		in.put(P_HABILITADO, alarma.getHabilitado());
		in.put(P_UMBRAL_SUPERIOR, alarma.getUmbralSuperior());
		in.put(P_UMBRAL_INFERIOR, alarma.getUmbralInferior());
		in.put(P_NOMBRE, alarma.getNombre());
		in.put(P_NOTIFICAR, alarma.getHabilitadoAvisoCelular());
		in.put(P_ID_SENSOR, alarma.getIdSensor());
		
		Map<String, Object> out = new HashMap<String, Object>();
		out.put(P_ID_ALARMA, alarma.getId());
		super.ejecutarStoredProcedure(ALTA, in, out, Alarma.class);
		alarma.setId((Long)out.get(P_ID_ALARMA.toLowerCase()));
		return alarma;
	}
	
//	IN PIdAlarma mediumint unsigned 
	public void delete (Long idAlarma) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_ALARMA, idAlarma);
		super.ejecutarStoredProcedure(BAJA, in, null, Alarma.class);
	}
	
//	IN Pidalarma mediumint unsigned,
//	IN Phabilitado bit(1),
//	IN Pumbralsuperior decimal(6,2) ,
//	IN Pumbralinferior decimal(6,2),
//	IN Pnombre varchar(100),
//	IN Pnotificar bit(1),
//	IN Pidsensor mediumint unsigned
	public Alarma update (Alarma alarma) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_ALARMA, alarma.getId());
		in.put(P_HABILITADO, alarma.getHabilitado());
		in.put(P_UMBRAL_SUPERIOR, alarma.getUmbralInferior());
		in.put(P_UMBRAL_INFERIOR, alarma.getUmbralInferior());
		in.put(P_NOMBRE, alarma.getNombre());
		in.put(P_NOTIFICAR, alarma.getHabilitadoAvisoCelular());
		in.put(P_ID_SENSOR, alarma.getIdSensor());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, Alarma.class);
		return alarma;
	}
}
