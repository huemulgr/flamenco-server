package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import com.ingenieriahuemul.flamencoserver.dominio.Usuario;

public interface UsuarioService {

    Usuario create(Usuario user);

//    User0 delete(int id);

    List<Usuario> findAll();


    Usuario update(Usuario user);
}
