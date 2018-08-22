package com.ingenieriahuemul.flamencoserver.domain;

import java.sql.Time;


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
	public String getPasswordConfiguracion() {
		return passwordConfiguracion;
	}
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

}
