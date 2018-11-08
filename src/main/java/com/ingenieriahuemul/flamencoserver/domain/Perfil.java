package com.ingenieriahuemul.flamencoserver.domain;

//SELECT 
//PERF.`idperfil` ID
//,PERF.`nombre`	Nombre
//,EMP.razonsocial		Empresa
//,group_concat(distinct USR.nombrecompleto) UsuariosAsignados
//,group_concat(distinct SEPE.idSensor) SensoresAsignados
//,count(SEPE.idSensor) CantidadSensores
public class Perfil {
	
	private Long id;
	private String nombre;
	private String empresa;
	private String usuariosAsignados;
	private String sensoresAsignados;
	private Integer cantidadSensores;
	
	private Long idEmpresa;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long idperfil) {
		this.id = idperfil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idempresa) {
		this.idEmpresa = idempresa;
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
	public String getSensoresAsignados() {
		return sensoresAsignados;
	}
	public void setSensoresAsignados(String sensoresAsignados) {
		this.sensoresAsignados = sensoresAsignados;
	}
		
}
