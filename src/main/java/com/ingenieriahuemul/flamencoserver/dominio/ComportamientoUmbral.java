package com.ingenieriahuemul.flamencoserver.dominio;

//SELECT 
//COMPU.`idcsu`			ID
//,COMPU.`habilitado`		Habilitado
//,COMPU.`umbralsuperior`	UmbralSup
//,COMPU.`umbralinferior`	UmbralInf
//,COMPU.`contactorentrada`	ContactorEntrada
//,COMPU.`contactorsalida`	ContactorSalida
//,COMPU.`umbralANDcontactor`	CondicionY
//,COMPU.`habilitarcontactorentrada`	HabilitarContEntrada
//,SENS.nombreregistro 				Sensor
//,ifnull(PSENS.nombre,'No ubicado') 	PuntoSensado
public class ComportamientoUmbral {
	
	private Long id;
	private Boolean habilitado;
	private Double umbralSup;
	private Double umbralInf;
	private Boolean contactorEntrada;
	private Boolean contactorSalida;
	private Boolean condicionY;
	private Boolean habilitarContEntrada;
	private String sensor;
	private String puntoSensado;
	
	private Long idSensor;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long idcsu) {
		this.id = idcsu;
	}
	public Double getUmbralSup() {
		return umbralSup;
	}
	public void setUmbralSup(Double umbralsuperior) {
		this.umbralSup = umbralsuperior;
	}
	public Double getUmbralInf() {
		return umbralInf;
	}
	public void setUmbralInf(Double umbralinferior) {
		this.umbralInf = umbralinferior;
	}
	public Long getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(Long idsensor) {
		this.idSensor = idsensor;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public String getPuntoSensado() {
		return puntoSensado;
	}
	public void setPuntoSensado(String puntoSensado) {
		this.puntoSensado = puntoSensado;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Boolean getContactorEntrada() {
		return contactorEntrada;
	}
	public void setContactorEntrada(Boolean contactorEntrada) {
		this.contactorEntrada = contactorEntrada;
	}
	public Boolean getContactorSalida() {
		return contactorSalida;
	}
	public void setContactorSalida(Boolean contactorSalida) {
		this.contactorSalida = contactorSalida;
	}
	public Boolean getCondicionY() {
		return condicionY;
	}
	public void setCondicionY(Boolean condicionY) {
		this.condicionY = condicionY;
	}
	public Boolean getHabilitarContEntrada() {
		return habilitarContEntrada;
	}
	public void setHabilitarContEntrada(Boolean habilitarContEntrada) {
		this.habilitarContEntrada = habilitarContEntrada;
	}	
		
}
