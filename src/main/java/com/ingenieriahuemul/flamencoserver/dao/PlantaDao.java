package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.Planta;

@Component
public class PlantaDao extends BaseDao{
	
	//parametros
	private static final String P_ID_PLANTA= "PidPlanta";
	private static final String P_NOMBRE = "Pnombre";
	private static final String P_HABILITADO = "Phabilitado";
	private static final String P_IMAGEN = "Pimagen";
	private static final String P_ID_EMPRESA = "Pidempresa";
	
	//stored procedures
	private static final String CONSULTA_1 = "flaPlantaSele";
	private static final String CONSULTA_2 = "flaPlantaEmpSele";
	private static final String ALTA = "flaPlantaAlta";
	private static final String BAJA = "flaPlantaBaja";	
	private static final String MODIFICACION = "flaPlantaModif";
		
	
	public List<Planta> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PLANTA, 0);
		return (List<Planta>) super.ejecutarStoredProcedure(CONSULTA_1, in, null, Planta.class);
	}
	
//	IN Pidalarma mediumint unsigned	
	public Planta findById(Long idPlanta) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PLANTA, idPlanta);
		return ((List<Planta>)super.ejecutarStoredProcedure(CONSULTA_1, in, null, Planta.class)).get(0);
	}
	
//	IN Pidalarma mediumint unsigned	
	public Planta findByIdEmpresa(Long idEmpresa) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, idEmpresa);
		return ((List<Planta>)super.ejecutarStoredProcedure(CONSULTA_2, in, null, Planta.class)).get(0);
	}
	
//	INOUT Pidplanta mediumint unsigned,
//	IN Pnombre VARCHAR(100),
//  IN Phabilitado boolean,
//  IN Pimagen VARCHAR(300),
//  IN Pidempresa mediumint unsigned
	public Planta save(Planta planta) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PLANTA, planta.getId());
		in.put(P_NOMBRE, planta.getNombre());
		in.put(P_HABILITADO, planta.getHabilitado());
		in.put(P_IMAGEN, planta.getRutaImagen());
		in.put(P_ID_EMPRESA, planta.getIdEmpresa());
		
		Map<String, Object> out = new HashMap<String, Object>();
		out.put(P_ID_PLANTA, planta.getId());
		super.ejecutarStoredProcedure(ALTA, in, out, Planta.class);
		planta.setId((Long)out.get(P_ID_PLANTA.toLowerCase()));
		return planta;
	}
	
//	IN PIdPlanta mediumint unsigned 
	public void delete (Long idPlanta) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PLANTA, idPlanta);
		super.ejecutarStoredProcedure(BAJA, in, null, Planta.class);
	}
	
//	IN Pidplanta mediumint unsigned,
//	IN Pnombre VARCHAR(100),
//  IN Phabilitado boolean,
//  IN Pimagen VARCHAR(300),
//  IN Pidempresa mediumint unsigned
	public Planta update (Planta alarma) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PLANTA, alarma.getId());
		in.put(P_NOMBRE, alarma.getNombre());
		in.put(P_HABILITADO, alarma.getHabilitado());
		in.put(P_IMAGEN, alarma.getRutaImagen());
		in.put(P_ID_EMPRESA, alarma.getIdEmpresa());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, Planta.class);
		return alarma;
	}
}
