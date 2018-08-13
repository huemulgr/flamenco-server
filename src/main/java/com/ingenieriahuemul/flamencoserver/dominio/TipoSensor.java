package com.ingenieriahuemul.flamencoserver.dominio;


//SELECT 
//TIPOS.`idtiposensor` ID
//,TIPOS.`nombre` Nombre
//,TIPOS.`minimo` Minimo
//,TIPOS.`maximo` Maximo
//,ifnull(group_concat(SENS.nombreregistro),'Ninguno') SensoresNombre
//,ifnull(group_concat(SENS.idsensor),'Ninguno') SensoresID
public class TipoSensor {
	
	private Long id;
	private String nombre;
	private Float minimo;
	private Float maximo;
	private String sensoresNombre;
	private String sensoresID;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long idtiposensor) {
		this.id = idtiposensor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getMinimo() {
		return minimo;
	}
	public void setMinimo(Float minimo) {
		this.minimo = minimo;
	}
	public Float getMaximo() {
		return maximo;
	}
	public void setMaximo(Float maximo) {
		this.maximo = maximo;
	}
	public String getSensoresNombre() {
		return sensoresNombre;
	}
	public void setSensoresNombre(String sensoresNombre) {
		this.sensoresNombre = sensoresNombre;
	}
	public String getSensoresID() {
		return sensoresID;
	}
	public void setSensoresID(String sensoresID) {
		this.sensoresID = sensoresID;
	}
	
}
