package com.ingenieriahuemul.flamencoserver.dominio;

//SELECT 
//USR.`idusuario`
//,USR.`nombreusuario`
//,USR.`nombrecompleto`
//,USR.`email`
//,group_concat(PERF.nombre) PerfilesAsignados
public class Usuario {

    private Long idusuario;
    private String nombreUsuario;
    private String nombreCompleto;
    private String email;
    
    //este parametro se usa para insertar o modificar la password de un user, 
    //pero quedaria vacio en todos los demas casos y es recomendable igualarla a null despues de usarla
    private String password;
    private String perfilesAsignados;
    
    	
	public Long getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreusuario) {
		this.nombreUsuario = nombreusuario;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombrecompleto) {
		this.nombreCompleto = nombrecompleto;
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
	public void setPerfilesAsignados(String string) {
		this.perfilesAsignados = string;
	}
	public String getPerfilesAsignados() {
		return perfilesAsignados;
	}   
    
}
