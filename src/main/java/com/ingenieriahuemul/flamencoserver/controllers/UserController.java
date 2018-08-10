package com.ingenieriahuemul.flamencoserver.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingenieriahuemul.flamencoserver.dominio.Usuario;
import com.ingenieriahuemul.flamencoserver.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
    
	@Autowired
    private UsuarioService usuarioService;

    
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
    	logger.info("creando usuario " + usuario + "...");
        return usuarioService.create(usuario);
    }

    @GetMapping(path = {"/{id}"})
    public Usuario findOne(@PathVariable("id") int id){
        return usuarioService.findById(id);
    }

    @PutMapping
    public Usuario update(@RequestBody Usuario usuario){
    	logger.info("actualizando usuario " + usuario + "...");
        return usuarioService.update(usuario);
    }

    @DeleteMapping(path = {"/{id}"})
    public void delete(@PathVariable("id") int idUsuario) {
    	logger.info("borrando usuario con id " + idUsuario + "...");
        usuarioService.delete(idUsuario);
    }

    @GetMapping
    public List findAll(){
        return usuarioService.findAll();
    }
}
