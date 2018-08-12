package com.ingenieriahuemul.flamencoserver.dominio;

//SELECT 
//PERF.`idperfil` ID
//,PERF.`nombre`	Nombre
//,EMP.razonsocial		Empresa
//,group_concat(distinct USR.nombrecompleto) UsuariosAsignados
//,group_concat(distinct SEPE.idSensor) SensoresAsignados
//,count(SEPE.idSensor) CantidadSensores
public class Perfil {
	
	//tabla
	private Long idperfil;
	private String nombre;
	private Long idempresa;
	
	//select
	private String empresa;
	private String usuariosAsignados;
	private Integer cantidadSensores;
	
	
	public Long getIdperfil() {
		return idperfil;
	}
	public void setIdperfil(Long idperfil) {
		this.idperfil = idperfil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(Long idempresa) {
		this.idempresa = idempresa;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getUsuariosAsignados() {
		return usuariosAsignados;
	}
	public void setUsuariosAsignados(String usuariosAsignados) {
		this.usuariosAsignados = usuariosAsignados;
	}
	public Integer getCantidadSensores() {
		return cantidadSensores;
	}
	public void setCantidadSensores(Integer cantidadSensores) {
		this.cantidadSensores = cantidadSensores;
	}
		
}
