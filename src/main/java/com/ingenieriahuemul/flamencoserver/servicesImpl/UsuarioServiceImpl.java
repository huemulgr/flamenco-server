package com.ingenieriahuemul.flamencoserver.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dominio.UsuarioDao;
import com.ingenieriahuemul.flamencoserver.dominio.PerfilDao;
import com.ingenieriahuemul.flamencoserver.dominio.Usuario;
import com.ingenieriahuemul.flamencoserver.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao repository;

    @Autowired
    private PerfilDao perfilDao;
    
    @Override
    public Usuario create(Usuario user) {
        return repository.save(user);
    }

//    @Override
//    public User0 delete(int id) {
//        User0 user = findById(id);
//        if(user != null){
//            repository.delete(user);
//        }
//        return user;
//    }

    @Override
    public List findAll() {
//        return repository.findAll();
    	return perfilDao.findAll();
    }


    @Override
    public Usuario update(Usuario user) {
        return null;
    }
}