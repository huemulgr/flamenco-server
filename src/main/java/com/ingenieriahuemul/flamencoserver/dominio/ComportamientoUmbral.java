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
	
	//tabla
	private Long idcsu;
	private Integer habilitado;
	private Double umbralsuperior;
	private Double umbralinferior;
	private Integer contactorentrada;
	private Integer contactorsalida;
	private Long idsensor;
	private Integer umbralANDcontactor;
	private Integer habilitarcontactorentrada;
	
	//select
	private String sensor;
	private String puntoSensado;
	
	
	public Long getIdcsu() {
		return idcsu;
	}
	public void setIdcsu(Long idcsu) {
		this.idcsu = idcsu;
	}
	public Double getUmbralsuperior() {
		return umbralsuperior;
	}
	public void setUmbralsuperior(Double umbralsuperior) {
		this.umbralsuperior = umbralsuperior;
	}
	public Double getUmbralinferior() {
		return umbralinferior;
	}
	public void setUmbralinferior(Double umbralinferior) {
		this.umbralinferior = umbralinferior;
	}
	public Long getIdsensor() {
		return idsensor;
	}
	public void setIdsensor(Long idsensor) {
		this.idsensor = idsensor;
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
	public Integer getContactorentrada() {
		return contactorentrada;
	}
	public void setContactorentrada(Integer contactorentrada) {
		contactorentrada = contactorentrada == 0 ? 0 : 1;
		this.contactorentrada = contactorentrada;
	}
	public Integer getContactorsalida() {
		return contactorsalida;
	}
	public void setContactorsalida(Integer contactorsalida) {
		contactorsalida = contactorsalida == 0 ? 0 : 1;
		this.contactorsalida = contactorsalida;
	}
	public Integer getUmbralANDcontactor() {
		return umbralANDcontactor;
	}
	public void setUmbralANDcontactor(Integer umbralANDcontactor) {
		umbralANDcontactor = umbralANDcontactor == 0 ? 0 : 1;
		this.umbralANDcontactor = umbralANDcontactor;
	}
	public Integer getHabilitarcontactorentrada() {
		return habilitarcontactorentrada;
	}
	public void setHabilitarcontactorentrada(Integer habilitarcontactorentrada) {
		habilitarcontactorentrada = habilitarcontactorentrada == 0 ? 0 : 1;
		this.habilitarcontactorentrada = habilitarcontactorentrada;
	}
	public Integer getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Integer habilitado) {
		habilitado = habilitado == 0 ? 0 : 1;
		this.habilitado = habilitado;
	}
		
}
