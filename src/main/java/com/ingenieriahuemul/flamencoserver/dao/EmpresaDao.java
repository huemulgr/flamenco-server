package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.domain.Empresa;

@Component
@SuppressWarnings("unchecked")
public class EmpresaDao extends BaseDao{
	
	//parametros
	private static final String P_ID_EMPRESA = "PidEmpresa";
	private static final String P_RAZON_SOCIAL = "PrazonSocial";
	private static final String P_ENCABEZADO_INFORME = "Pencabezadoinforme";
	private static final String P_PASSWORD_CONFIGURACION = "PpasswordConfiguracion";
	private static final String P_HORA_1ER_REGISTRO = "PhoraPrimerRegistro";
	private static final String P_PERIODO_IMPRESION = "PperiodoImpresion";
	
	//stored procedures
	private static final String CONSULTA = "flaEmpresaSele";
	private static final String ALTA = "flaEmpresaAlta";
	private static final String BAJA = "flaEmpresaBaja";	
	private static final String MODIFICACION = "flaEmpresaModif";
	
//	IN `Pidempresa` MEDIUMINT UNSIGNED	
	public List<Empresa> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, 0);
		return (List<Empresa>) super.ejecutarStoredProcedure(CONSULTA, in, null, Empresa.class);
	}
	
//	IN `PIdempresa` MEDIUMINT UNSIGNED	
	public Empresa findById(Long idEmpresa) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, idEmpresa);
		return ((List<Empresa>)super.ejecutarStoredProcedure(CONSULTA, in, null, Empresa.class)).get(0);
	}
	
//	INOUT PIdEmpresa mediumint unsigned,
//	IN Prazonsocial VARCHAR(100),
//  IN PencabezadoInforme TEXT,
//  IN PpasswordConfiguracion VARCHAR(20),
//  IN PhoraPrimerRegistro VARCHAR(20),
//  IN PperiodoImpresion tinyint unsigned
	public Empresa save(Empresa empresa) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, empresa.getId());
		in.put(P_RAZON_SOCIAL, empresa.getRazonSocial());
		in.put(P_ENCABEZADO_INFORME, empresa.getEncabezado());
		in.put(P_PASSWORD_CONFIGURACION, empresa.getPasswordConfiguracion());
		in.put(P_HORA_1ER_REGISTRO, empresa.getPrimerRegistro());
		in.put(P_PERIODO_IMPRESION, empresa.getPeriodo());
		
		Map<String, Object> out = new HashMap<String, Object>();
		super.ejecutarStoredProcedure(ALTA, in, out, Empresa.class);
		empresa.setId((Long) out.get(P_ID_EMPRESA.toLowerCase()));
		return empresa;
	}
	
//	IN PIdEmpresa mediumint unsigned 
	public void delete (Long idEmpresa) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, idEmpresa);
		super.ejecutarStoredProcedure(BAJA, in, null, Empresa.class);
	}
	
//	IN PIdEmpresa mediumint unsigned,
//	IN Prazonsocial VARCHAR(100),
//  IN PencabezadoInforme TEXT,
//  IN PpasswordConfiguracion VARCHAR(20),
//  IN PhoraPrimerRegistro VARCHAR(20),
//  IN PperiodoImpresion tinyint unsigned
	public Empresa update (Empresa empresa) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_EMPRESA, empresa.getId());
		in.put(P_RAZON_SOCIAL, empresa.getRazonSocial());
		in.put(P_ENCABEZADO_INFORME, empresa.getEncabezado());
		in.put(P_PASSWORD_CONFIGURACION, empresa.getPasswordConfiguracion());
		in.put(P_HORA_1ER_REGISTRO, empresa.getPrimerRegistro());
		in.put(P_PERIODO_IMPRESION, empresa.getPeriodo());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, Empresa.class);
		return empresa;
	}
	
	//int ambos
	private static final String P_MB_LIBRES = "PespacioLibreMB";
	private static final String P_MIN_MUESTREO = "PminutosMuestreo";	
	
	private static final String ESPACIO_LIBRE = "flaCalcularEspacio";
	public EspacioLibreRetorno espacioLibre(Integer espacioLibre, Integer minutosMuestreo) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_MB_LIBRES, espacioLibre);
		in.put(P_MIN_MUESTREO, minutosMuestreo);
		
		return ((List<EspacioLibreRetorno>)super.ejecutarStoredProcedure(ESPACIO_LIBRE, in, null, EspacioLibreRetorno.class)).get(0);		
	}
	
	public static class EspacioLibreRetorno {
		private Integer aniosAutonomia;
		private Integer mesesAutonomia;
		
		public EspacioLibreRetorno() {}
		
		public Integer getAniosAutonomia() { return aniosAutonomia; }
		public void setAniosAutonomia(Integer aniosAutonomia) {	this.aniosAutonomia = aniosAutonomia; }
		public Integer getMesesAutonomia() { return mesesAutonomia; }
		public void setMesesAutonomia(Integer mesesAutonomia) {	this.mesesAutonomia = mesesAutonomia; }		
	}
}
