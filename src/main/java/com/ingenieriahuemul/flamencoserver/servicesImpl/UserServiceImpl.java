package com.ingenieriahuemul.flamencoserver.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenieriahuemul.flamencoserver.dominio.DaoUser0;
import com.ingenieriahuemul.flamencoserver.dominio.User0;
import com.ingenieriahuemul.flamencoserver.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DaoUser0 repository;

    @Override
    public User0 create(User0 user) {
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
        return repository.findAll();
    }


    @Override
    public User0 update(User0 user) {
        return null;
    }
}