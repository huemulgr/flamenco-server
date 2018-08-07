package com.ingenieriahuemul.flamencoserver.dominio;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class User0 {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;
    @Column
    private String nombreusuario;
    @Column
    private String nombrecompleto;
    @Column
    private String email;
    @Column
    private String password;
    
    
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getNombreusuario() {
		return nombreusuario;
	}
	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}
	public String getNombrecompleto() {
		return nombrecompleto;
	}
	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
   
    
}
