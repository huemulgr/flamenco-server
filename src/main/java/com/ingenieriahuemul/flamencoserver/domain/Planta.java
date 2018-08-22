package com.ingenieriahuemul.flamencoserver.domain;

//SELECT 
//PLAN.`idplanta` 	ID
//,PLAN.`nombre`		Nombre
//,PLAN.`habilitado` 	Habilitado
//,ifnull(PLAN.`imagen`,'No definido')		RutaImagen
//,EMP.razonsocial	RazonSocial
public class Planta {

	private Long id;
	private String nombre;
	private Boolean habilitado;
	private String rutaImagen;
	private String razonSocial;
	
	private Long idEmpresa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long idplanta) {
		this.id = idplanta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String imagen) {
		this.rutaImagen = imagen;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idempresa) {
		this.idEmpresa = idempresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String empresa) {
		this.razonSocial = empresa;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
		
}
