package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.dominio.PuntoDeSensado;

@Component
public class PuntoDeSensadoDao extends BaseDao{
	
	//parametros
	private static final String P_ID_PUNTO_DE_SENSADO = "Pidpuntosensado";
	private static final String P_HABILITADO = "Phabilitado";
	private static final String P_NOMBRE_REGISTRO = "Pnombreregistro";
	private static final String P_NOMBRE_CORTO = "Pnombrecorto";
	private static final String P_NOMBRE_LARGO = "Pnombrelargo";
	private static final String P_ORDEN_IMPRESION = "Pordenimpresion";
	private static final String P_ORDEN_GRILLA = "Pordengrilla";
	private static final String P_COORDENADA_X = "PcoordenadaX";
	private static final String P_COORDENADA_Y = "PcoordenadaY";	
	private static final String P_ID_PLANTA = "Pidplanta";
	private static final String P_PAGINA_REPORTE = "PpaginaReporte";
	
	//stored procedures
	private static final String CONSULTA = "flaPuntoDeSensadoSele";
	private static final String ALTA = "flaPuntoDeSensadoAlta";
	private static final String BAJA = "flaPuntoDeSensadoBaja";	
	private static final String MODIFICACION = "flaPuntoDeSensadoModif";
		
	
	public List<PuntoDeSensado> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PUNTO_DE_SENSADO, 0);
		return (List<PuntoDeSensado>) super.ejecutarStoredProcedure(CONSULTA, in, null, PuntoDeSensado.class);
	}
	
//	IN Pidalarma mediumint unsigned	
	public PuntoDeSensado findById(Integer idPuntoDeSensado) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PUNTO_DE_SENSADO, idPuntoDeSensado);
		return ((List<PuntoDeSensado>)super.ejecutarStoredProcedure(CONSULTA, in, null, PuntoDeSensado.class)).get(0);
	}
	
//	INOUT PIdpuntosensado int unsigned,
//  IN Phabilitado boolean,
//	IN Pnombreregistro varchar(10) ,
//	IN Pnombrecorto char(5),
//	IN Pnombrelargo varchar(100),
//	IN Pordenimpresion int unsigned,
//  IN Pordengrilla int unsigned,
//  IN PcoordenadaX int,
//  IN PcoordenadaY int,
//  IN Pidplanta mediumint unsigned,
//  IN PpaginaReporte tinyint unsigned
	public PuntoDeSensado save(PuntoDeSensado puntoDeSensado) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PUNTO_DE_SENSADO, puntoDeSensado.getIdpuntoSensado());
		in.put(P_HABILITADO, puntoDeSensado.getHabilitado());
		in.put(P_NOMBRE_REGISTRO, puntoDeSensado.getNombreregistro());
		in.put(P_NOMBRE_CORTO, puntoDeSensado.getNombrecorto());
		in.put(P_NOMBRE_LARGO, puntoDeSensado.getNombrelargo());
		in.put(P_ORDEN_IMPRESION, puntoDeSensado.getOrdenimpresion());
		in.put(P_ORDEN_GRILLA, puntoDeSensado.getOrdengrilla());
		in.put(P_COORDENADA_X, puntoDeSensado.getCoordenadaY());
		in.put(P_COORDENADA_Y, puntoDeSensado.getCoordenadaX());
		in.put(P_ID_PLANTA, puntoDeSensado.getIdplanta());
		in.put(P_PAGINA_REPORTE, puntoDeSensado.getPagina());
				
		Map<String, Object> out = new HashMap<String, Object>();
		out.put(P_ID_PUNTO_DE_SENSADO, puntoDeSensado.getIdpuntoSensado());
		super.ejecutarStoredProcedure(ALTA, in, out, PuntoDeSensado.class);
		puntoDeSensado.setIdpuntoSensado((Long)out.get(P_ID_PUNTO_DE_SENSADO.toLowerCase()));
		return puntoDeSensado;
	}
	
//	IN PIdPuntoDeSensado mediumint unsigned 
	public void delete (Integer idPuntoDeSensado) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PUNTO_DE_SENSADO, idPuntoDeSensado);
		super.ejecutarStoredProcedure(BAJA, in, null, PuntoDeSensado.class);
	}
	
//	IN PIdpuntosensado int unsigned,
//  IN Phabilitado boolean,
//	IN Pnombreregistro varchar(10) ,
//	IN Pnombrecorto char(5),
//	IN Pnombrelargo varchar(100),
//	IN Pordenimpresion int unsigned,
//  IN Pordengrilla int unsigned,
//  IN PcoordenadaX int,
//  IN PcoordenadaY int,
//  IN Pidplanta mediumint unsigned,
//	IN PpaginaReporte tinyint unsigned
	public PuntoDeSensado update (PuntoDeSensado puntoDeSensado) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PUNTO_DE_SENSADO, puntoDeSensado.getIdpuntoSensado());
		in.put(P_HABILITADO, puntoDeSensado.getHabilitado());
		in.put(P_NOMBRE_REGISTRO, puntoDeSensado.getNombreregistro());
		in.put(P_NOMBRE_CORTO, puntoDeSensado.getNombrecorto());
		in.put(P_NOMBRE_LARGO, puntoDeSensado.getNombrelargo());
		in.put(P_ORDEN_IMPRESION, puntoDeSensado.getOrdenimpresion());
		in.put(P_ORDEN_GRILLA, puntoDeSensado.getOrdengrilla());
		in.put(P_COORDENADA_X, puntoDeSensado.getCoordenadaY());
		in.put(P_COORDENADA_Y, puntoDeSensado.getCoordenadaX());
		in.put(P_ID_PLANTA, puntoDeSensado.getIdplanta());
		in.put(P_PAGINA_REPORTE, puntoDeSensado.getPagina());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, PuntoDeSensado.class);
		return puntoDeSensado;
	}
}
