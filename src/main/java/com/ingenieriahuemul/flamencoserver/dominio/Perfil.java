package com.ingenieriahuemul.flamencoserver.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idperfil;
	
	@Column
	private String nombre;
	
	@Column
	private int idempresa;

	
	public int getIdperfil() {
		return idperfil;
	}

	public void setIdperfil(int idperfil) {
		this.idperfil = idperfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdempresa() {
		return idempresa;
	}

	public void setIdempresa(int idEmpresa) {
		this.idempresa = idEmpresa;
	}
	
	
}
