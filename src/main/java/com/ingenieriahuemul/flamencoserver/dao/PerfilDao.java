package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.Perfil;
import com.ingenieriahuemul.flamencoserver.domain.Sensor;

@Component
@SuppressWarnings("unchecked")
public class PerfilDao extends BaseDao{
	
	//parametros
	private static final String P_ID_PERFIL = "PidPerfil";
	private static final String P_NOMBRE = "Pnombre";
	private static final String P_ID_EMPRESA = "PIdEmpresa";
	private static final String P_ID_SENSOR = "PIdSensor";
	private static final String P_ID_USUARIO = "PIdUsuario";
	private static final String P_CASCADA = "Pcascada";
	
	//stored procedures
	private static final String CONSULTA = "flaPerfilSele";
	private static final String ALTA = "flaPerfilAlta";
	private static final String BAJA = "flaPerfilBaja";	
	private static final String MODIFICACION = "flaPerfilModif";
	private static final String ASIGNAR_USUARIO = "flaAsignarPerfilUsuario";	
	private static final String QUITAR_USUARIO = "flaQuitarPerfilUsuario";
	private static final String ASIGNAR_SENSOR = "flaAsignarSensorPerfil";
	private static final String QUITAR_SENSOR = "flaQuitarSensorPerfil";
	private static final String SENSORES_ASIGNADOS = "flaPerfilSensoresSele";

	public List<Sensor> findSensoresAsignados(Long idPerfil) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PERFIL, idPerfil);
		return (List<Sensor>) super.ejecutarStoredProcedure(SENSORES_ASIGNADOS, in, null, Sensor.class);
	}	
	
//	IN `Pidperfil` MEDIUMINT UNSIGNED	
	public List<Perfil> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PERFIL, 0);
		return (List<Perfil>) super.ejecutarStoredProcedure(CONSULTA, in, null, Perfil.class);
	}
	
//	IN `Pidperfil` MEDIUMINT UNSIGNED	
	public Perfil findById(Long idPerfil) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PERFIL, idPerfil);
		return ((List<Perfil>)super.ejecutarStoredProcedure(CONSULTA, in, null, Perfil.class)).get(0);
	}
	
//	INOUT PidPerfil mediumint unsigned,
//	IN Pnombre VARCHAR(100),
//  IN PIdEmpresa mediumint unsigned
	public Perfil save(Perfil perfil) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, perfil.getIdEmpresa());
		in.put(P_NOMBRE, perfil.getNombre());
		in.put(P_ID_PERFIL, perfil.getId());
		
		Map<String, Object> out = new HashMap<String, Object>();
		super.ejecutarStoredProcedure(ALTA, in, out, Perfil.class);
		perfil.setId((Long) out.get(P_ID_PERFIL.toLowerCase()));
		return perfil;
	}
	
//	IN PIdPerfil mediumint unsigned 
	public void delete (Long idPerfil) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PERFIL, idPerfil);
		in.put(P_CASCADA, false);
		super.ejecutarStoredProcedure(BAJA, in, null, Perfil.class);
	}
	
//	IN Pidperfil mediumint unsigned,
//  IN Pnombre VARCHAR(100)
	public Perfil update (Perfil usuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PERFIL, usuario.getId());
		in.put(P_NOMBRE, usuario.getNombre());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, Perfil.class);
		return usuario;
	}
	
//	IN PIdusuario mediumint unsigned,
//	IN PIdperfil mediumint unsigned	
	public void asignarUsuario (Long idPerfil, Long idUsuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, idUsuario);
		in.put(P_ID_PERFIL, idPerfil);
		
		super.ejecutarStoredProcedure(ASIGNAR_USUARIO, in, null, Perfil.class);
	}
	
	public void quitarUsuario (Long idPerfil, Long idUsuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, idUsuario);
		in.put(P_ID_PERFIL, idPerfil);
		
		super.ejecutarStoredProcedure(QUITAR_USUARIO, in, null, Perfil.class);
	}
	
//	IN PIdPerfil mediumint unsigned,
//  IN PIdSensor mediumint unsigned
	public void asignarSensor (Long idPerfil, Long idSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PERFIL, idPerfil);
		in.put(P_ID_SENSOR, idSensor);
		
		super.ejecutarStoredProcedure(ASIGNAR_SENSOR, in, null, Perfil.class);
	}
	
	public void quitarSensor (Long idPerfil, Long idSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_PERFIL, idPerfil);
		in.put(P_ID_SENSOR, idSensor);
		
		super.ejecutarStoredProcedure(QUITAR_SENSOR, in, null, Perfil.class);
	}
}
