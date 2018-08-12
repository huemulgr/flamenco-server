package com.ingenieriahuemul.flamencoserver.dominio;

//SELECT 
//USR.`idusuario`
//,USR.`nombreusuario`
//,USR.`nombrecompleto`
//,USR.`email`
//,group_concat(PERF.nombre) PerfilesAsignados
public class Usuario {

    private Long idusuario;
    private String nombreusuario;
    private String nombrecompleto;
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
	public void setPerfilesAsignados(String string) {
		this.perfilesAsignados = string;
	}
	public String getPerfilesAsignados() {
		return perfilesAsignados;
	}   
    
}
