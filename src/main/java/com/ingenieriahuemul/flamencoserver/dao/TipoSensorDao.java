package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.TipoSensor;

@Component
public class TipoSensorDao extends BaseDao{
	
	//parametros
	private static final String P_ID_TIPO_SENSOR = "PidTipoSensor";
	private static final String P_NOMBRE = "PnombreTipoSensor";
	private static final String P_MINIMO = "PnombreCompleto";
	private static final String P_MAXIMO = "Pemail";
	
	//stored procedures
	private static final String CONSULTA = "flaTipoSensorSele";
	private static final String ALTA = "flaTipoSensorAlta";
	private static final String BAJA = "flaTipoSensorBaja";	
	private static final String MODIFICACION = "flaTipoSensorModif";
	
//	IN `PidTipoSensor` MEDIUMINT UNSIGNED	
	public List<TipoSensor> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_TIPO_SENSOR, 0);
		return (List<TipoSensor>) super.ejecutarStoredProcedure(CONSULTA, in, null, TipoSensor.class);
	}
	
//	IN `PIdTipoSensor` MEDIUMINT UNSIGNED	
	public TipoSensor findById(Long idTipoSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_TIPO_SENSOR, idTipoSensor);
		return ((List<TipoSensor>)super.ejecutarStoredProcedure(CONSULTA, in, null, TipoSensor.class)).get(0);
	}
	
//	INOUT Pidtiposensor mediumint unsigned,
//	IN Pnombre varchar(100),
//  IN Pminimo decimal(6,2),
//  IN Pmaximo decimal(6,2)
	public TipoSensor save(TipoSensor TipoSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_TIPO_SENSOR, TipoSensor.getId());
		in.put(P_NOMBRE, TipoSensor.getNombre());
		in.put(P_MINIMO, TipoSensor.getMinimo());
		in.put(P_MAXIMO, TipoSensor.getMaximo());
		
		Map<String, Object> out = new HashMap<String, Object>();
		super.ejecutarStoredProcedure(ALTA, in, out, TipoSensor.class);
		TipoSensor.setId((Long) out.get(P_ID_TIPO_SENSOR.toLowerCase()));
		return TipoSensor;
	}
	
//	IN PIdTipoSensor mediumint unsigned 
	public void delete (Long idTipoSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_TIPO_SENSOR, idTipoSensor);
		super.ejecutarStoredProcedure(BAJA, in, null, TipoSensor.class);
	}
	
//	IN Pidtiposensor mediumint unsigned,
//	IN Pnombre varchar(100),
//  IN Pminimo decimal(6,2),
//  IN Pmaximo decimal(6,2)
	public TipoSensor update (TipoSensor TipoSensor) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_TIPO_SENSOR, TipoSensor.getId());
		in.put(P_NOMBRE, TipoSensor.getNombre());
		in.put(P_MINIMO, TipoSensor.getMinimo());
		in.put(P_MAXIMO, TipoSensor.getMaximo());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, TipoSensor.class);
		return TipoSensor;
	}
	
}
