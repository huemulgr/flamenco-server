package com.ingenieriahuemul.flamencoserver.services;

import java.util.List;

import com.ingenieriahuemul.flamencoserver.dominio.User0;

public interface UserService {

    User0 create(User0 user);

//    User0 delete(int id);

    List<User0> findAll();


    User0 update(User0 user);
}
