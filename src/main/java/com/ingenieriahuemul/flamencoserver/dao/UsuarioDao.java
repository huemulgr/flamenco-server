package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenieriahuemul.flamencoserver.dominio.Usuario;

@Component
public class UsuarioDao extends BaseDao{
	
	//parametros
	private final String P_ID_USUARIO = "PidUsuario";
	private final String P_NOMBRE_USUARIO = "PnombreUsuario";
	private final String P_NOMBRE_COMPLETO = "PnombreCompleto";
	private final String P_EMAIL = "Pemail";
	private final String P_PASSWORD = "Ppassword";
	
	//stored procedures
	private final String CONSULTA = "flaUsuarioSele";
	private final String ALTA = "flaUsuarioAlta";
	private final String BAJA = "flaUsuarioBaja";	
	private final String MODIFICACION = "flaUsuarioModif";
		
	
	//	IN `Pidusuario` MEDIUMINT UNSIGNED	
	public List<Usuario> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, 0);
		return (List<Usuario>) super.ejecutarStoredProcedure(CONSULTA, in, null, Usuario.class);
	}
	
	//	IN `Pidusuario` MEDIUMINT UNSIGNED	
	public Usuario findById(Integer idUsuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, idUsuario);
		return ((List<Usuario>)super.ejecutarStoredProcedure(CONSULTA, in, null, Usuario.class)).get(0);
	}
	
	//	OUT Pidusuario mediumint unsigned,
	//	IN PnombreUsuario char(15),
	//  IN PnombreCompleto VARCHAR(100),
	//  IN Ppassword CHAR(20),
	//  IN Pemail varchar(40)
	public Usuario save(Usuario usuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_NOMBRE_USUARIO, usuario.getNombreusuario());
		in.put(P_NOMBRE_USUARIO, usuario.getNombrecompleto());
		in.put(P_PASSWORD, usuario.getPassword());
		in.put(P_EMAIL, usuario.getEmail());
		
		Map<String, Object> out = new HashMap<String, Object>();
		super.ejecutarStoredProcedure(ALTA, in, out, Usuario.class);
		usuario.setIdusuario((Integer) out.get(P_ID_USUARIO.toLowerCase()));
		return usuario;
	}
	
	//	IN PIdUsuario mediumint unsigned 
	public void delete (Integer idUsuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, idUsuario);
		super.ejecutarStoredProcedure(BAJA, in, null, Usuario.class);
	}
	
	//	IN PIdusuario mediumint unsigned,
	//	IN PnombreUsuario char(15),
	//  IN PnombreCompleto VARCHAR(100),
	//  IN Ppassword CHAR(20),
	//  IN Pemail varchar(40)
	public Usuario update (Usuario usuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, usuario.getNombreusuario());
		in.put(P_NOMBRE_USUARIO, usuario.getNombreusuario());
		in.put(P_NOMBRE_USUARIO, usuario.getNombrecompleto());
		in.put(P_PASSWORD, usuario.getPassword());
		in.put(P_EMAIL, usuario.getEmail());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, Usuario.class);
		return usuario;
	}
}
