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
	private Integer habilitado;
	private Double umbralSup;
	private Double umbralInf;
	private Integer contactorEntrada;
	private Integer contactorSalida;
	private Integer condicionY;
	private Integer habilitarContEntrada;
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
	public Integer getContactorEntrada() {
		return contactorEntrada;
	}
	public void setContactorEntrada(Integer contactorentrada) {
		contactorentrada = contactorentrada == 0 ? 0 : 1;
		this.contactorEntrada = contactorentrada;
	}
	public Integer getContactorSalida() {
		return contactorSalida;
	}
	public void setContactorSalida(Integer contactorsalida) {
		contactorsalida = contactorsalida == 0 ? 0 : 1;
		this.contactorSalida = contactorsalida;
	}
	public Integer getCondicionY() {
		return condicionY;
	}
	public void setCondicionY(Integer umbralANDcontactor) {
		umbralANDcontactor = umbralANDcontactor == 0 ? 0 : 1;
		this.condicionY = umbralANDcontactor;
	}
	public Integer getHabilitarContEntrada() {
		return habilitarContEntrada;
	}
	public void setHabilitarContEntrada(Integer habilitarcontactorentrada) {
		habilitarcontactorentrada = habilitarcontactorentrada == 0 ? 0 : 1;
		this.habilitarContEntrada = habilitarcontactorentrada;
	}
	public Integer getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Integer habilitado) {
		habilitado = habilitado == 0 ? 0 : 1;
		this.habilitado = habilitado;
	}
		
}
