package com.ingenieriahuemul.flamencoserver.dominio;

//SELECT 
//
//ifnull(PLAN.nombre,'No asignado')				Planta
//,ifnull(PSEN.idpuntoSensado,'No asignado')			PuntoSensado
//,SEN.`idsensor` 		ID
//,PSEN.`nombreregistro`	Nombre
//,PSEN.`nombrecorto`		NombreAbreviado
//,PSEN.`nombrelargo`		Descripcion
//,PSEN.`ordenimpresion`	OrdenImpresion
//,PSEN.`ordengrilla`		OrdenGrilla
//,SEN.`minutosmuestreonube` MinutosMuestreo
//,SEN.`ubicacion`		DescripcionUbicacion
//,SEN.`deltamuestreonube` DeltaMuestreo
//,SEN.`habilitado`		Habilitado
//,TSEN.nombre			Tipo
//,ifnull(SEN.`mac`,'00000000')				MAC
//,ifnull(SEN.`maccoordinador`,'00000000')	MACdelCoordinador
//,ifnull((select 1 from alarma where idsensor=SEN.idsensor limit 1),0) TieneAlarma
//,ifnull((select 1 from comportamientohora where idsensor=SEN.idsensor limit 1),0) TieneCompHora
//,ifnull((select 1 from comportamientosensorumbral where idsensor=SEN.idsensor limit 1),0) TieneCompUmbral
//#        ,ifnull((select 1 from medicionsensorpunto where idsensor=SEN.idsensor limit 1),0) TieneMedicion
//,ifnull((select 1 from sensorperfil where idsensor=SEN.idsensor limit 1),0) AsignadoPerfil
public class Sensor {
	
	//tabla
	private Long id;
	private Integer minutosMuestreo;
	private String descripcionUbicacion;
	private Double deltaMuestreo;
	private Integer habilitado;
	private Long idtiposensor;
	private String mac;
	private String macDelCoordinador;
	private Long idpuntodesensado;

	//select
	private String nombre;
	private String nombreAbreviado;
	private String descripcion;
	private Integer ordenImpresion;
	private Integer ordenGrilla;
	private String planta;
	private String puntoSensado;
	private String tipo;
	private Integer tieneAlarma;
	private Integer tieneCompHora;
	private Integer tieneCompUmbral;
	private Integer asignadoPerfil;
	
	
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getPuntoSensado() {
		return puntoSensado;
	}
	public void setPuntoSensado(String puntoSensado) {
		this.puntoSensado = puntoSensado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreAbreviado() {
		return nombreAbreviado;
	}
	public void setNombreAbreviado(String nombreAbreviado) {
		this.nombreAbreviado = nombreAbreviado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getOrdenImpresion() {
		return ordenImpresion;
	}
	public void setOrdenImpresion(Integer ordenImpresion) {
		this.ordenImpresion = ordenImpresion;
	}
	public Integer getOrdenGrilla() {
		return ordenGrilla;
	}
	public void setOrdenGrilla(Integer ordenGrilla) {
		this.ordenGrilla = ordenGrilla;
	}
	public Integer getMinutosMuestreo() {
		return minutosMuestreo;
	}
	public void setMinutosMuestreo(Integer minutosMuestreo) {
		this.minutosMuestreo = minutosMuestreo;
	}
	public String getDescripcionUbicacion() {
		return descripcionUbicacion;
	}
	public void setDescripcionUbicacion(String descripcionUbicacion) {
		this.descripcionUbicacion = descripcionUbicacion;
	}
	public Double getDeltaMuestreo() {
		return deltaMuestreo;
	}
	public void setDeltaMuestreo(Double deltaMuestreo) {
		this.deltaMuestreo = deltaMuestreo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMacDelCoordinador() {
		return macDelCoordinador;
	}
	public Long getIdtiposensor() {
		return idtiposensor;
	}
	public void setIdtiposensor(Long idtiposensor) {
		this.idtiposensor = idtiposensor;
	}
	public void setMacDelCoordinador(String macDelCoordinador) {
		this.macDelCoordinador = macDelCoordinador;
	}
	public Long getIdpuntodesensado() {
		return idpuntodesensado;
	}
	public void setIdpuntodesensado(Long idpuntodesensado) {
		this.idpuntodesensado = idpuntodesensado;
	}	
	public Integer getTieneAlarma() {
		return tieneAlarma;
	}
	public void setTieneAlarma(Integer tieneAlarma) {
		tieneAlarma = tieneAlarma == 0 ? 0 : 1;
		this.tieneAlarma = tieneAlarma;
	}
	public Integer getTieneCompHora() {
		return tieneCompHora;
	}
	public void setTieneCompHora(Integer tieneCompHora) {
		tieneCompHora = tieneCompHora == 0 ? 0 : 1;
		this.tieneCompHora = tieneCompHora;
	}
	public Integer getTieneCompUmbral() {
		return tieneCompUmbral;
	}
	public void setTieneCompUmbral(Integer tieneCompUmbral) {
		tieneCompUmbral = tieneCompUmbral == 0 ? 0 : 1;
		this.tieneCompUmbral = tieneCompUmbral;
	}
	public Integer getAsignadoPerfil() {
		return asignadoPerfil;
	}
	public void setAsignadoPerfil(Integer asignadoPerfil) {
		asignadoPerfil = asignadoPerfil == 0 ? 0 : 1;
		this.asignadoPerfil = asignadoPerfil;
	}
	public Integer getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Integer habilitado) {
		habilitado = habilitado == 0 ? 0 : 1;
		this.habilitado = habilitado;
	}
}
