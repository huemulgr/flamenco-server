package com.ingenieriahuemul.flamencoserver.domain;

import java.sql.Time;
import java.sql.Date;

//select 	PSENS.nombrecorto Registro
//, REG.fecha Fecha
//, time_format(REG.hora,'%H:%i') Hora
//, MED.valor Valor
//,PSENS.idpuntoSensado	IDPuntoSensado
//,PSENS.idplanta			IDPlanta
public class Lectura {
	private String registro;
	private Date fecha;
	private Time hora;
	private Double valor;
	private Long idPuntoSensado;
	private Long idPlanta;
	
	
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getIdPuntoSensado() {
		return idPuntoSensado;
	}
	public void setIdPuntoSensado(Long idPuntoSensado) {
		this.idPuntoSensado = idPuntoSensado;
	}
	public Long getIdPlanta() {
		return idPlanta;
	}
	public void setIdPlanta(Long idPlanta) {
		this.idPlanta = idPlanta;
	}
	
}
