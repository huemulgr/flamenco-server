package com.ingenieriahuemul.flamencoserver.dominio;

//SELECT 
//PLAN.`idplanta` 	ID
//,PLAN.`nombre`		Nombre
//,PLAN.`habilitado` 	Habilitado
//,ifnull(PLAN.`imagen`,'No definido')		RutaImagen
//,EMP.razonsocial	RazonSocial
public class Planta {

	private Long id;
	private String nombre;
	private Integer habilitado;
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
	public Integer getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Integer habilitado) {
		habilitado = habilitado == 0 ? 0 : 1;
		this.habilitado = habilitado;
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
		
}
