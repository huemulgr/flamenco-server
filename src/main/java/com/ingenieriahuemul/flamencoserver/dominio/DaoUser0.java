package com.ingenieriahuemul.flamencoserver.dominio;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface DaoUser0 extends Repository<User0, Integer> {

    void delete(User0 user);

    List<User0> findAll();

    User0 save(User0 user);
}
