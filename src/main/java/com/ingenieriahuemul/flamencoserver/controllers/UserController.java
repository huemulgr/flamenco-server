package com.ingenieriahuemul.flamencoserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ingenieriahuemul.flamencoserver.dominio.User0;
import com.ingenieriahuemul.flamencoserver.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User0 create(@RequestBody User0 user){
        return userService.create(user);
    }

//    @GetMapping(path = {"/api/{id}"})
//    public User0 findOne(@PathVariable("id") int id){
//        return userService.findById(id);
//    }

    @PutMapping
    public User0 update(@RequestBody User0 user){
        return userService.update(user);
    }

//    @DeleteMapping(path ={"/{id}"})
//    public User0 delete(@PathVariable("id") int id) {
//        return userService.delete(id);
//    }

    @GetMapping
    public List findAll(){
        return userService.findAll();
    }
}
