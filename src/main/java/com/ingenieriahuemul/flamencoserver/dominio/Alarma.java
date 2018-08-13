package com.ingenieriahuemul.flamencoserver.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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
	private Integer habilitado;
	private Double umbralSuperior;
	private Double umbralInferior;
	private String nombre;
	private Integer habilitadoAvisoCelular;
	private Long idSensor;
	private String sensor;
	private String puntoSensado;
		
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
	public Integer getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Integer habilitado) {
		habilitado = habilitado == 0 ? 0 : 1;
		this.habilitado = habilitado;
	}
	public Integer getHabilitadoAvisoCelular() {
		return habilitadoAvisoCelular;
	}
	protected void setHabilitadoAvisoCelular(Integer notificar) {
		notificar = notificar == 0 ? 0 : 1;
		this.habilitadoAvisoCelular = notificar;
	}
	
}
