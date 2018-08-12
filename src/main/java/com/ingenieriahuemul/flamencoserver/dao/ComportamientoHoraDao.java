package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.dominio.ComportamientoHora;

@Component
public class ComportamientoHoraDao extends BaseDao{
	
	//parametros
	private static final String P_ID_COMP_HORA = "Pidch";
	private static final String P_HABILITADO = "Phabilitado";
	private static final String P_HORA_INICIO = "Phorainicio";
	private static final String P_HORA_FIN = "Phorafin";
	private static final String P_PERIODO = "Pperiodo";
	private static final String P_CONTACTOR_SALIDA = "Pcontactorsalida";
	private static final String P_ID_SENSOR = "Pidsensor";
	
	//stored procedures
	private static final String CONSULTA = "flaCompHoraSele";
	private static final String ALTA = "flaCompoHoraAlta";
	private static final String BAJA = "flaCompoHoraBaja";	
	private static final String MODIFICACION = "flaCompHoraModif";
		
	
	public List<ComportamientoHora> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_HORA, 0);
		return (List<ComportamientoHora>) super.ejecutarStoredProcedure(CONSULTA, in, null, ComportamientoHora.class);
	}
	
//	IN Pidalarma mediumint unsigned	
	public ComportamientoHora findById(Integer idComportamientoHora) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_COMP_HORA, idComportamientoHora);
		return ((List<ComportamientoHora>)super.ejecutarStoredProcedure(CONSULTA, in, null, ComportamientoHora.class)).get(0);
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
		in.put(P_ID_COMP_HORA, compHora.getIdch());
		in.put(P_HABILITADO, compHora.getHabilitado());
		in.put(P_HORA_INICIO, compHora.getHoraInicio());
		in.put(P_HORA_FIN, compHora.getHoraFin());
		in.put(P_PERIODO, compHora.getPeriodo());
		in.put(P_CONTACTOR_SALIDA, compHora.getContactorSalida());
		in.put(P_ID_SENSOR, compHora.getIdsensor());
		
		Map<String, Object> out = new HashMap<String, Object>();
		out.put(P_ID_COMP_HORA, compHora.getIdch());
		super.ejecutarStoredProcedure(ALTA, in, out, ComportamientoHora.class);
		compHora.setIdch((Long)out.get(P_ID_COMP_HORA.toLowerCase()));
		return compHora;
	}
	
//	IN PIdComportamientoHora mediumint unsigned 
	public void delete (Integer idComportamientoHora) {
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
		in.put(P_ID_COMP_HORA, compHora.getIdch());
		in.put(P_HABILITADO, compHora.getHabilitado());
		in.put(P_HORA_INICIO, compHora.getHoraInicio());
		in.put(P_HORA_FIN, compHora.getHoraFin());
		in.put(P_PERIODO, compHora.getPeriodo());
		in.put(P_ID_SENSOR, compHora.getIdsensor());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, ComportamientoHora.class);
		return compHora;
	}
}
