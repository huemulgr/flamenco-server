package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dao.UsuarioDao;
import com.ingenieriahuemul.flamencoserver.dominio.Usuario;

@Service
public class UsuarioService {
	private static final Logger logger = Logger.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	
    public Usuario create(Usuario usuario) {
    	usuarioDao.save(usuario);
    	return usuario;
    }

    public void delete(int idUsuario) {
    	usuarioDao.delete(idUsuario);
    }

    public List<Usuario> findAll() {
    	return usuarioDao.findAll();
    }
    
    public Usuario findById(Integer idUsuario) {
    	return usuarioDao.findById(idUsuario);
    }

    public Usuario update(Usuario usuario) {
    	usuarioDao.update(usuario);
    	return usuario;
    }
}
