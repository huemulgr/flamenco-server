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
	
	private Long id;
	private Boolean habilitado;
	private String nombre;		//alias nombreregistro
	private Long coordX;
	private Long coordY;
	private String planta;
	private String nombreCorto;
	private String descripcion;	//alias nombrelargo
	private Integer ordenImpresion;
	private Integer ordenGrilla;
	private Integer pagina;
	private Long sensorAsociado;
	private Boolean mediciones;
	
	private Long idPlanta;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long idpuntoSensado) {
		this.id = idpuntoSensado;
	}
	public Long getCoordX() {
		return coordX;
	}
	public void setCoordX(Long coordenadaX) {
		this.coordX = coordenadaX;
	}
	public Long getCoordY() {
		return coordY;
	}
	public void setCoordY(Long coordenadaY) {
		this.coordY = coordenadaY;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public Long getIdPlanta() {
		return idPlanta;
	}
	public void setIdPlanta(Long idplanta) {
		this.idPlanta = idplanta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombreregistro) {
		this.nombre = nombreregistro;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombrecorto) {
		this.nombreCorto = nombrecorto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String nombrelargo) {
		this.descripcion = nombrelargo;
	}
	public Integer getOrdenImpresion() {
		return ordenImpresion;
	}
	public void setOrdenImpresion(Integer ordenimpresion) {
		this.ordenImpresion = ordenimpresion;
	}
	public Integer getOrdenGrilla() {
		return ordenGrilla;
	}
	public void setOrdenGrilla(Integer ordengrilla) {
		this.ordenGrilla = ordengrilla;
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
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Boolean getMediciones() {
		return mediciones;
	}
	public void setMediciones(Boolean mediciones) {
		this.mediciones = mediciones;
	}
	
}
