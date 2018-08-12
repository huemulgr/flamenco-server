package com.ingenieriahuemul.flamencoserver.dominio;

import java.util.Date;


//SELECT 
//EMP.`idempresa`				ID
//,EMP.`razonsocial`			RazonSocial
//,EMP.`encabezadoinforme`	Encabezado
//#		,EMP.`passwordconfiguracion`	
//,EMP.`horaprimerregistro`	PrimerRegistro
//,EMP.`periodoimpresion`		Periodo
public class Empresa {
	
	//tabla
	private Long idempresa;
	private String razonsocial;
	private String encabezadoinforme;
	private String passwordconfiguracion;
	private Date horaprimerregistro;
	private Integer periodoimpresion;
	private Integer columnasImpresion;
	
	
	public Long getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(Long idempresa) {
		this.idempresa = idempresa;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	public String getEncabezadoinforme() {
		return encabezadoinforme;
	}
	public void setEncabezadoinforme(String encabezadoinforme) {
		this.encabezadoinforme = encabezadoinforme;
	}
	public String getPasswordconfiguracion() {
		return passwordconfiguracion;
	}
	public void setPasswordconfiguracion(String passwordconfiguracion) {
		this.passwordconfiguracion = passwordconfiguracion;
	}
	public Date getHoraprimerregistro() {
		return horaprimerregistro;
	}
	public void setHoraprimerregistro(Date horaprimerregistro) {
		this.horaprimerregistro = horaprimerregistro;
	}
	public Integer getPeriodoimpresion() {
		return periodoimpresion;
	}
	public void setPeriodoimpresion(Integer periodoimpresion) {
		this.periodoimpresion = periodoimpresion;
	}
	public Integer getColumnasImpresion() {
		return columnasImpresion;
	}
	public void setColumnasImpresion(Integer columnasImpresion) {
		this.columnasImpresion = columnasImpresion;
	}

}
