package com.ingenieriahuemul.flamencoserver.dominio;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface UsuarioDao extends Repository<Usuario, Integer> {

    void delete(Usuario user);

    List<Usuario> findAll();

    Usuario save(Usuario user);
}
