package com.ingenieriahuemul.flamencoserver.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EstadoMas {
	private Long idPuntoSensado;
	private Boolean habilitado;
	private Double valor;
	private Long fechaHoraMilis;
	private Timestamp fechaHora;
	private Integer coordenadaX;
	private Integer coordenadaY;
	private Long idPlanta;
	private String nombreCorto;
	private String nombreLargo;
	private String nombreRegistro;
	private Boolean estadoR1E;
	private Boolean estadoR2E;
	private Boolean estadoR1S;
	private Boolean estadoR2S;
	
	
	//fecha y hora la recibo de la bd como timestamp, para json tambien lo paso como milisegundos que es mas facil de parsear
	public Long getFechaHoraMilis() {
		return fechaHora.getTime();
	}
	public void setFechaHoraMilis(Long fechaHoraMilis) {
		this.fechaHoraMilis = fechaHoraMilis;
		this.fechaHora = new Timestamp(fechaHoraMilis);
	}
	//esto devuelve la fecha y hora actual en formato YYYY-MM-DDTHH:mm:ss.SSS+Z. 
	public Timestamp getFechaHora() {
		return fechaHora;
	}
	public Long getIdPuntoSensado() {
		return idPuntoSensado;
	}
	public void setIdPuntoSensado(Long idPuntoSensado) {
		this.idPuntoSensado = idPuntoSensado;
	}
	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
		this.fechaHoraMilis = this.fechaHora.getTime();
	}
	
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public Integer getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public Long getIdPlanta() {
		return idPlanta;
	}
	public void setIdPlanta(Long idPlanta) {
		this.idPlanta = idPlanta;
	}
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getNombreLargo() {
		return nombreLargo;
	}
	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	public String getNombreRegistro() {
		return nombreRegistro;
	}
	public void setNombreRegistro(String nombreRegistro) {
		this.nombreRegistro = nombreRegistro;
	}
	public Boolean getEstadoR1E() {
		return estadoR1E;
	}
	public void setEstadoR1E(Boolean estadoR1E) {
		this.estadoR1E = estadoR1E;
	}
	public Boolean getEstadoR2E() {
		return estadoR2E;
	}
	public void setEstadoR2E(Boolean estadoR2E) {
		this.estadoR2E = estadoR2E;
	}
	public Boolean getEstadoR1S() {
		return estadoR1S;
	}
	public void setEstadoR1S(Boolean estadoR1S) {
		this.estadoR1S = estadoR1S;
	}
	public Boolean getEstadoR2S() {
		return estadoR2S;
	}
	public void setEstadoR2S(Boolean estadoR2S) {
		this.estadoR2S = estadoR2S;
	}
		
}
