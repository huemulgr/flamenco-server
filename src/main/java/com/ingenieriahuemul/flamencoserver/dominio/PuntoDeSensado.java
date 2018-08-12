package com.ingenieriahuemul.flamencoserver.dominio;

//SELECT 
//PSEN.`idpuntoSensado`	ID
//,PSEN.`habilitado`		Habilitado
//,PSEN.`nombreregistro`	Nombre
//,PSEN.`coordenadaX`		CoordX
//,PSEN.`coordenadaY`		CoordY
//,PLAN.`nombre`			Planta
//,PSEN.`nombrecorto`		NombreCorto
//,PSEN.`nombrelargo`		Descripcion
//,PSEN.`ordenimpresion`	OrdenImpresion
//,PSEN.`ordengrilla`		OrdenGrilla
//,PSEN.`paginaReporte`	Pagina
//,ifnull((select idsensor from sensor where idpuntoSensado=PSEN.idpuntoSensado limit 1),0) SensorAsociado
//,ifnull((select 1 from medicion MED where MED.idpuntoSensado=PSEN.idpuntoSensado limit 1),0) Mediciones
public class PuntoDeSensado {
	
	//tabla
	private Long idpuntoSensado;
	private Integer habilitado;
	private Long coordenadaX;
	private Long coordenadaY;
	private Long idplanta;
	private String nombreregistro;
	private String nombrecorto;
	private String nombrelargo;
	private Integer ordenimpresion;
	private Integer ordengrilla;
	private Integer pagina;
	
	//select
	private Long sensorAsociado;
	private Integer mediciones;
	
	
	public Long getIdpuntoSensado() {
		return idpuntoSensado;
	}
	public void setIdpuntoSensado(Long idpuntoSensado) {
		this.idpuntoSensado = idpuntoSensado;
	}
	public Integer getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Integer habilitado) {
		habilitado = habilitado == 0 ? 0 : 1;
		this.habilitado = habilitado;
	}
	public Long getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(Long coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public Long getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(Long coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public Long getIdplanta() {
		return idplanta;
	}
	public void setIdplanta(Long idplanta) {
		this.idplanta = idplanta;
	}
	public String getNombreregistro() {
		return nombreregistro;
	}
	public void setNombreregistro(String nombreregistro) {
		this.nombreregistro = nombreregistro;
	}
	public String getNombrecorto() {
		return nombrecorto;
	}
	public void setNombrecorto(String nombrecorto) {
		this.nombrecorto = nombrecorto;
	}
	public String getNombrelargo() {
		return nombrelargo;
	}
	public void setNombrelargo(String nombrelargo) {
		this.nombrelargo = nombrelargo;
	}
	public Integer getOrdenimpresion() {
		return ordenimpresion;
	}
	public void setOrdenimpresion(Integer ordenimpresion) {
		this.ordenimpresion = ordenimpresion;
	}
	public Integer getOrdengrilla() {
		return ordengrilla;
	}
	public void setOrdengrilla(Integer ordengrilla) {
		this.ordengrilla = ordengrilla;
	}
	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	public Long getSensorAsociado() {
		return sensorAsociado;
	}
	public void setSensorAsociado(Long sensorAsociado) {
		this.sensorAsociado = sensorAsociado;
	}
	public Integer getMediciones() {
		return mediciones;
	}
	public void setMediciones(Integer mediciones) {
		mediciones = mediciones == 0 ? 0 : 1;
		this.mediciones = mediciones;
	}
	
}
