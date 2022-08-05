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

import com.prueba.tienda.models.EmpresaModel;
import com.prueba.tienda.services.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
   @Autowired
   EmpresaService usuarioService;

   @GetMapping()
   public ArrayList<EmpresaModel> getAll(){
    return usuarioService.getAll();
   }

   @PostMapping()
   public EmpresaModel saveEmpresa(@RequestBody EmpresaModel usuario){
    return usuarioService.save(usuario);
   }
   @GetMapping( path = "/{id}")
    public Optional<EmpresaModel> getById(@PathVariable("id") Long id) {
        return this.usuarioService.findById(id);
    }

    @DeleteMapping( path = "/{id}")
    public String deleteEmpresaById(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.deleteById(id);
        if (ok){
            return "Se eliminó le empresa con id " + id;
        }else{
            return "No pudo la empresa con id" + id;
        }
    }
}
