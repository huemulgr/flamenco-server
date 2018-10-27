package com.ingenieriahuemul.flamencoserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.ingenieriahuemul.flamencoserver.domain.Usuario;

@Component
public class UsuarioDao extends BaseDao{
	
	//parametros
	private static final String P_ID_USUARIO = "PidUsuario";
	private static final String P_NOMBRE_USUARIO = "PnombreUsuario";
	private static final String P_NOMBRE_COMPLETO = "PnombreCompleto";
	private static final String P_EMAIL = "Pemail";
	private static final String P_PASSWORD = "Ppassword";
	
	//stored procedures
	private static final String CONSULTA = "flaUsuarioSele";
	private static final String ALTA = "flaUsuarioAlta";
	private static final String BAJA = "flaUsuarioBaja";	
	private static final String MODIFICACION = "flaUsuarioModif";
	private static final String AUTENTICACION = "flaValidaUsuario"; 
	private static final String OBTENER_SENSORES = "flaUsuarioPerfilSensorSele"; 
	
	
	//  IN Pnombreusuario char(15),
	//	IN Ppassword char(20)
	public AutenticacionResultado autenticar(String usr, String pass) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_NOMBRE_USUARIO, usr);
		in.put(P_PASSWORD, pass);		

		return ((List<AutenticacionResultado>) super.ejecutarStoredProcedure(AUTENTICACION, in, null, AutenticacionResultado.class)).get(0);
	}	
	
	public List<UsuarioSensorPerfil> obtenerSensoresUsuario(Long idUsuario) {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, idUsuario);
		return (List<UsuarioSensorPerfil>) super.ejecutarStoredProcedure(OBTENER_SENSORES, in, null, UsuarioSensorPerfil.class);
	}
	
	//	IN `Pidusuario` MEDIUMINT UNSIGNED	
	public List<Usuario> findAll() {
		Map<String, Object> in = new HashMap<String, Object>();
		in.put(P_ID_USUARIO, 0);
		return (List<Usuario>) super.ejecutarStoredProcedure(CONSULTA, in, null, Usuario.class);
	}
	
	//	IN `Pidusuario` MEDIUMINT UNSIGNED	
	public Usuario findById(Long idUsuario) {
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
		in.put(P_NOMBRE_USUARIO, usuario.getNombreUsuario());
		in.put(P_NOMBRE_COMPLETO, usuario.getNombreCompleto());
		in.put(P_PASSWORD, usuario.getPassword());
		in.put(P_EMAIL, usuario.getEmail());
		
		Map<String, Object> out = new HashMap<String, Object>();
		super.ejecutarStoredProcedure(ALTA, in, out, Usuario.class);
		usuario.setIdusuario((Long) out.get(P_ID_USUARIO.toLowerCase()));
		return usuario;
	}
	
	//	IN PIdUsuario mediumint unsigned 
	public void delete (Long idUsuario) {
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
		in.put(P_ID_USUARIO, usuario.getIdusuario());
		in.put(P_NOMBRE_USUARIO, usuario.getNombreUsuario());
		in.put(P_NOMBRE_COMPLETO, usuario.getNombreCompleto());
		in.put(P_PASSWORD, usuario.getPassword());
		in.put(P_EMAIL, usuario.getEmail());
		
		super.ejecutarStoredProcedure(MODIFICACION, in, null, Usuario.class);
		return usuario;
	}
	
	
//TODO: mover a archivo propio
	public static class AutenticacionResultado {
		Long Autorizacion;
		Long Usuario;
		
		public AutenticacionResultado() { }

		public Long getAutorizacion() {
			return Autorizacion;
		}

		public void setAutorizacion(Long autorizacion) {
			Autorizacion = autorizacion;
		}

		public Long getUsuario() {
			return Usuario;
		}

		public void setUsuario(Long usuario) {
			Usuario = usuario;
		}		
	}
	
	public static class UsuarioSensorPerfil {
		Long idUsuario;
		String nombreUsuario;
		String nombreCompleto;
		String email;
		String perfil;
		Long idPerfil;
		String mac;
		Long idSensor;
		public Long getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(Long idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getNombreUsuario() {
			return nombreUsuario;
		}
		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
		public String getNombreCompleto() {
			return nombreCompleto;
		}
		public void setNombreCompleto(String nombreCompleto) {
			this.nombreCompleto = nombreCompleto;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPerfil() {
			return perfil;
		}
		public void setPerfil(String perfil) {
			this.perfil = perfil;
		}
		public Long getIdPerfil() {
			return idPerfil;
		}
		public void setIdPerfil(Long idPerfil) {
			this.idPerfil = idPerfil;
		}
		public String getMac() {
			return mac;
		}
		public void setMac(String mac) {
			this.mac = mac;
		}
		public Long getIdSensor() {
			return idSensor;
		}
		public void setIdSensor(Long idSensor) {
			this.idSensor = idSensor;
		}
	}
}
