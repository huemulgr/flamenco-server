package com.ingenieriahuemul.flamencoserver.domain;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


//SELECT 
//EMP.`idempresa`				ID
//,EMP.`razonsocial`			RazonSocial
//,EMP.`encabezadoinforme`	Encabezado
//#		,EMP.`passwordconfiguracion`	
//,EMP.`horaprimerregistro`	PrimerRegistro
//,EMP.`periodoimpresion`		Periodo
public class Empresa {
	
	//tabla
	private Long id;
	private String razonSocial;
	private String encabezado;
	private String passwordConfiguracion;
	private Time primerRegistro;
	private Integer periodo;
	
	private Integer columnasImpresion;
	
	private String qr;
	
	//campo para facilitar la comparacion del pass
	private String passwordActual;

	@JsonIgnore
	public String getPasswordActual() {
		return passwordActual;
	}
	@JsonProperty
	public void setPasswordActual(String passwordActual) {
		this.passwordActual = passwordActual;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long idempresa) {
		this.id = idempresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonsocial) {
		this.razonSocial = razonsocial;
	}
	public String getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(String encabezadoinforme) {
		this.encabezado = encabezadoinforme;
	}
	@JsonIgnore
	public String getPasswordConfiguracion() {
		return passwordConfiguracion;
	}
	@JsonProperty
	public void setPasswordConfiguracion(String passwordconfiguracion) {
		this.passwordConfiguracion = passwordconfiguracion;
	}
	public Time getPrimerRegistro() {
		return primerRegistro;
	}
	public void setPrimerRegistro(Time horaprimerregistro) {
		this.primerRegistro = horaprimerregistro;
	}
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodoimpresion) {
		this.periodo = periodoimpresion;
	}
	public Integer getColumnasImpresion() {
		return columnasImpresion;
	}
	public void setColumnasImpresion(Integer columnasImpresion) {
		this.columnasImpresion = columnasImpresion;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
	}

}
