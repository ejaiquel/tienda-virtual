package com.prueba.tienda.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tienda.models.UsuarioModel;
import com.prueba.tienda.services.UsuarioService;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
   @Autowired
   UsuarioService usuarioService;

   @GetMapping()
   public ArrayList<UsuarioModel> getUsuarios(){
    return usuarioService.getUsuarios();
   }

   @PostMapping()
   public UsuarioModel saveUsuario(@RequestBody UsuarioModel usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        return usuarioService.saveUsuario(usuario);
   }

   @GetMapping( path = "/{id}")
    public Optional<UsuarioModel> getUsuarioById(@PathVariable("id") Long id) {
        return this.usuarioService.findById(id);
    }    

    @DeleteMapping( path = "/{id}")
    public String deleteUsuarioById(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.deleteUsuarioById(id);
        if (ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No pudo eliminar el usuario con id" + id;
        }
    }
}
