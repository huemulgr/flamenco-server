package com.ingenieriahuemul.flamencoserver.dominio;

public class Planta {
	
	//tabla
	private Long idplanta;
	private String nombre;
	private Integer habilitado;
	private String imagen;
	private Long idempresa;
	
	
	public Long getIdplanta() {
		return idplanta;
	}
	public void setIdplanta(Long idplanta) {
		this.idplanta = idplanta;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Long getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(Long idempresa) {
		this.idempresa = idempresa;
	}
		
}
