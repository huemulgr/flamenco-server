package com.ingenieriahuemul.flamencoserver.dominio;

import java.sql.Time;

//COMPH.`idch`	ID
//,COMPH.`horaInicio`	HoraInicio
//,COMPH.`horaFin`	HoraFin
//,COMPH.`periodo`	Periodo
//,COMPH.`contactorSalida`	ContactorSalida
//,COMPH.`habilitado`	Habilitado
//,COMPH.idSensor IDSensor
//,SENS.nombreregistro Sensor
//,ifnull(PSENS.nombre,'No ubicado') PuntoSensado
public class ComportamientoHora {

	private Long id;
	private Time horaInicio;
	private Time horaFin;
	private Time periodo;
	private Integer contactorSalida;
	private Integer habilitado;
	private Long idSensor;
	private String sensor;
	private String puntoSensado;
		
	
	public Long getId() {
		return id;
	}
	public void setId(Long idch) {
		this.id = idch;
	}
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Time getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}
	public Time getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Time periodo) {
		this.periodo = periodo;
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
	public Integer getContactorSalida() {
		return contactorSalida;
	}
	public void setContactorSalida(Integer contactorSalida) {
		contactorSalida = contactorSalida == 0 ? 0 : 1;
		this.contactorSalida = contactorSalida;
	}
	public Integer getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Integer habilitado) {
		habilitado = habilitado == 0 ? 0 : 1;
		this.habilitado = habilitado;
	}
}
