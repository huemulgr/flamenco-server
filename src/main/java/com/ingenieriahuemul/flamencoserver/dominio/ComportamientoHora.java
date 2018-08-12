package com.ingenieriahuemul.flamencoserver.dominio;

import java.util.Date;

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

//revisar comportamiento campos fecha	
	//tabla
	private Long idch;
	private Integer habilitado;
	private Date horaInicio;
	private Date periodo;
	private Date horaFin;
	private Integer contactorSalida;
	private Long idsensor;
	
	//select
	private String sensor;
	private String puntoSensado;
	
	
	public Long getIdch() {
		return idch;
	}
	public void setIdch(Long idch) {
		this.idch = idch;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}
	public Date getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
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
