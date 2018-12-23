package com.ingenieriahuemul.flamencoserver.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

//Select 
//ALA.idalarma ID
//, ALA.habilitado Habilitado
//, ALA.umbralsuperior UmbralSuperior
//, ALA.umbralinferior UmbralInferior
//, ALA.nombre Nombre
//, ALA.notificar HabilitadoAvisoCelular
//, idSensor IDSensor
//, PSENS.nombreregistro Sensor
//, ifnull(PSENS.nombrecorto,'No ubicado') PuntoSensado
public class Alarma {
	
	private Long id;
	private Boolean habilitado;
	private Double umbralSuperior;
	private Double umbralInferior;
	private String nombre;	
	private Boolean habilitadoAvisoCelular;
	
	private Long idSensor;
	private String sensor;
	private String puntoSensado;
		
	//variable para comprobar si la alarma esta disparada o no
	@JsonIgnore private Boolean alarmaOn;
	
	public Long getId() {
		return id;
	}
	public void setId(Long idAlarma) {
		this.id = idAlarma;
	}
	public Double getUmbralSuperior() {
		return umbralSuperior;
	}
	public void setUmbralSuperior(Double umbralSuperior) {
		this.umbralSuperior = umbralSuperior;
	}
	public Double getUmbralInferior() {
		return umbralInferior;
	}
	public void setUmbralInferior(Double umbralInferior) {
		this.umbralInferior = umbralInferior;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPuntoSensado() {
		return puntoSensado;
	}
	public void setPuntoSensado(String puntoSensado) {
		this.puntoSensado = puntoSensado;
	}
	public Long getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(Long idSensor) {
		this.idSensor = idSensor;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public Boolean getHabilitadoAvisoCelular() {
		return habilitadoAvisoCelular;
	}
	public void setHabilitadoAvisoCelular(Boolean habilitadoAvisoCelular) {
		this.habilitadoAvisoCelular = habilitadoAvisoCelular;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Boolean getAlarmaOn() {
		return alarmaOn;
	}
	public void setAlarmaOn(Boolean alarmaOn) {
		this.alarmaOn = alarmaOn;
	}
	
}
