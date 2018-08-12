package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.dominio.Sensor;

@Component
public class SensorDao extends BaseDao{
	
	//parametros
	private static final String P_ID_SENSOR = "PidSensor";
	private static final String P_UBICACION = "Pubicacion";
	private static final String P_MINUTOS_MUESTREO_NUBE = "Pminutosmuestreonube";
	private static final String P_DELTA_MUESTREO_NUBE = "Pdeltamuestreonube";
	private static final String P_HABILITADO = "Phabilitado";
	private static final String P_ID_TIPO_SENSOR = "Pidtiposensor";
	private static final String P_MAC = "Pmac";
	private static final String P_MAC_COORDINADOR = "Pmaccoordinador";
	private static final String P_ID_PUNTO_SENSADO = "Pidpuntosensado";	
	
	//stored procedures
	private static final String CONSULTA = "flaSensorSele";
	private static final String ALTA = "flaSensorAlta";
	private static final String BAJA = "flaSensorBaja";	
	private static final String MODIFICACION = "flaSensorModif";
		
	
	public List<Sensor> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, 0);
		return (List<Sensor>) super.ejecutarStoredProcedure(CONSULTA, in, null, Sensor.class);
	}
	
//	IN Pidalarma mediumint unsigned	
	public Sensor findById(Integer idSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, idSensor);
		return ((List<Sensor>)super.ejecutarStoredProcedure(CONSULTA, in, null, Sensor.class)).get(0);
	}
	
//	INOUT Pidsensor mediumint,
//	IN Pminutosmuestreonube int unsigned,
//	IN Pubicacion varchar(100),
//	IN Pdeltamuestreonube decimal(6,2),
//	IN Phabilitado bit(1),
//	IN Pidtiposensor mediumint unsigned,
//	IN Pmac	char(12),
//  IN Pmaccoordinador char(12),
//  IN Pidpuntosensado int unsigned
	public Sensor save(Sensor sensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, sensor.getId());
		in.put(P_MINUTOS_MUESTREO_NUBE, sensor.getMinutosMuestreo());
		in.put(P_UBICACION, sensor.getDescripcionUbicacion());
		in.put(P_DELTA_MUESTREO_NUBE, sensor.getDeltaMuestreo());
		in.put(P_HABILITADO, sensor.getHabilitado());
		in.put(P_ID_TIPO_SENSOR, sensor.getIdtiposensor());
		in.put(P_MAC, sensor.getMac());
		in.put(P_MAC_COORDINADOR, sensor.getMacDelCoordinador());
		in.put(P_ID_PUNTO_SENSADO, sensor.getIdpuntodesensado());
		
		Map<String, Object> out = new HashMap<String, Object>();
		out.put(P_ID_SENSOR, sensor.getId());
		super.ejecutarStoredProcedure(ALTA, in, out, Sensor.class);
		sensor.setId((Long)out.get(P_ID_SENSOR.toLowerCase()));
		return sensor;
	}
	
//	IN PIdSensor mediumint unsigned 
	public void delete (Integer idSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, idSensor);
		super.ejecutarStoredProcedure(BAJA, in, null, Sensor.class);
	}
	
//	IN Pidsensor mediumint,
//	IN Pminutosmuestreonube int unsigned,
//	IN Pubicacion varchar(100),
//	IN Pdeltamuestreonube decimal(6,2),
//	IN Phabilitado bit(1),
//	IN Pidtiposensor mediumint unsigned,
//  IN Pmac	char(12),
//  IN Pmaccoordinador char(12),
//  IN Pidpuntosensado int unsigned
	public Sensor update (Sensor sensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_SENSOR, sensor.getId());
		in.put(P_MINUTOS_MUESTREO_NUBE, sensor.getMinutosMuestreo());
		in.put(P_UBICACION, sensor.getDescripcionUbicacion());
		in.put(P_DELTA_MUESTREO_NUBE, sensor.getDeltaMuestreo());
		in.put(P_HABILITADO, sensor.getHabilitado());
		in.put(P_ID_TIPO_SENSOR, sensor.getIdtiposensor());
		in.put(P_MAC, sensor.getMac());
		in.put(P_MAC_COORDINADOR, sensor.getMacDelCoordinador());
		in.put(P_ID_PUNTO_SENSADO, sensor.getIdpuntodesensado());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, Sensor.class);
		return sensor;
	}
}
