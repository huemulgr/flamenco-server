package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.ComportamientoHora;

@Component
public class ComportamientoHoraDao extends BaseDao{

//los campos Time es mas sencillo guardarlos como un string con el formato "HH:MM:ss", falta decidir donde validar. Por ahora si estan mal los rebota el SP pero podria hacerse x aplicacion	
	//parametros
	private static final String P_ID_COMP_HORA = "Pidch";
	private static final String P_HABILITADO = "Phabilitado";
	private static final String P_HORA_INICIO = "Phorainicio";
	private static final String P_HORA_FIN = "Phorafin";
	private static final String P_PERIODO = "Pperiodo";
	private static final String P_CONTACTOR_SALIDA = "Pcontactorsalida";
	private static final String P_ID_SENSOR = "Pidsensor";
	private static final String P_CONTACTOR_ENTRADA = "Pcontactorentrada";
	private static final String P_AND_ENTRADA = "Pumbralandcontactor";
	private static final String P_HABILITAR_CE = "Phabilitarcontentrada";
	
	//stored procedures
	private static final String CONSULTA = "flaCompHoraSele";
	private static final String CONSULTA_SENSOR = "flaCompHoraSensorSele";
	private static final String ALTA = "flaCompHoraAlta";
	private static final String BAJA = "flaCompHoraBaja";	
	private static final String MODIFICACION = "flaCompHoraModif";
		
	
	public List<ComportamientoHora> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_HORA, 0);
		return (List<ComportamientoHora>) super.ejecutarStoredProcedure(CONSULTA, in, null, ComportamientoHora.class);
	}
	
//	IN Pidch mediumint unsigned	
	public ComportamientoHora findById(Long idComportamientoHora) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_HORA, idComportamientoHora);
		return ((List<ComportamientoHora>)super.ejecutarStoredProcedure(CONSULTA, in, null, ComportamientoHora.class)).get(0);
	}
	
//	IN Pidsensor mediumint unsigned	
	public List<ComportamientoHora> findByIdSensor(Long idSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, idSensor);
		return (List<ComportamientoHora>)super.ejecutarStoredProcedure(CONSULTA_SENSOR, in, null, ComportamientoHora.class);
	}
	
//	INOUT Pidch mediumint unsigned,
//	IN Phabilitado bit(1),
//	IN Phorainicio time,
//	IN Phorafin time,
//	IN Pperiodo time,
//	IN Pcontactorsalida bit,
//	IN Pidsensor mediumint unsigned
	public ComportamientoHora save(ComportamientoHora compHora) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_HORA, compHora.getId());
		in.put(P_HABILITADO, compHora.getHabilitado());
		in.put(P_HORA_INICIO, compHora.getHoraInicio());
		in.put(P_HORA_FIN, compHora.getHoraFin());
		in.put(P_PERIODO, compHora.getPeriodo());
		in.put(P_CONTACTOR_SALIDA, compHora.getContactorSalida());
		in.put(P_ID_SENSOR, compHora.getIdSensor());
		in.put(P_CONTACTOR_ENTRADA, compHora.getContactorEntrada());
		in.put(P_AND_ENTRADA, compHora.getCondicionY());
		in.put(P_HABILITAR_CE, compHora.getHabilitarContEntrada());
		
		Map<String, Object> out = new HashMap<String, Object>();
		out.put(P_ID_COMP_HORA, compHora.getId());
		super.ejecutarStoredProcedure(ALTA, in, out, ComportamientoHora.class);
		compHora.setId((Long)out.get(P_ID_COMP_HORA.toLowerCase()));
		return compHora;
	}
	
//	IN PIdComportamientoHora mediumint unsigned 
	public void delete (Long idComportamientoHora) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_HORA, idComportamientoHora);
		super.ejecutarStoredProcedure(BAJA, in, null, ComportamientoHora.class);
	}
	
//	IN Pidch mediumint unsigned,
//	IN Phabilitado bit(1),
//	IN Phorainicio time,
//	IN Phorafin time,
//	IN Pperiodo time,
//	IN Pcontactorsalida bit,
//	IN Pidsensor mediumint unsigned
	public ComportamientoHora update (ComportamientoHora compHora) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_HORA, compHora.getId());
		in.put(P_HABILITADO, compHora.getHabilitado());
		in.put(P_HORA_INICIO, compHora.getHoraInicio());
		in.put(P_HORA_FIN, compHora.getHoraFin());
		in.put(P_PERIODO, compHora.getPeriodo());
		in.put(P_CONTACTOR_SALIDA, compHora.getContactorSalida());
		in.put(P_ID_SENSOR, compHora.getIdSensor());
		in.put(P_CONTACTOR_ENTRADA, compHora.getContactorEntrada());
		in.put(P_AND_ENTRADA, compHora.getCondicionY());
		in.put(P_HABILITAR_CE, compHora.getHabilitarContEntrada());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, ComportamientoHora.class);
		return compHora;
	}
}
