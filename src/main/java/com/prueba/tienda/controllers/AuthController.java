package com.prueba.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tienda.models.UsuarioModel;
import com.prueba.tienda.services.UsuarioService;
import com.prueba.tienda.utils.JWTUtil;

@RestController
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody UsuarioModel usuario) {

        UsuarioModel usuarioLogueado = usuarioService.getCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }
}
