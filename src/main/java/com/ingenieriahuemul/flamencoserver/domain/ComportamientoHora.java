package com.ingenieriahuemul.flamencoserver.domain;

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
	private Boolean contactorSalida;
	private Boolean habilitado;
	private Long idSensor;
	private Boolean contactorEntrada;
	private Boolean condicionY;
	private Boolean habilitarContEntrada;
	private String sensor;
	private String puntoSensado;
		
	
	public int getNroContactorEntrada() {
		int numero = contactorEntrada!=null && contactorEntrada == true ? 1 : 0;
		return numero;
	}
	public int getNroContactorSalida() {
		int numero = contactorSalida == false ? 0 : 1;
		return numero;
	}
	
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
	public Boolean getContactorSalida() {
		return contactorSalida;
	}
	public void setContactorSalida(Boolean contactorSalida) {
		this.contactorSalida = contactorSalida;
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
