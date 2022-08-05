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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tienda.models.MarcaModel;
import com.prueba.tienda.services.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
   @Autowired
   MarcaService marcaService;

   @GetMapping()
   public ArrayList<MarcaModel> getAll(){
    return marcaService.getAll();
   }

   @PostMapping()
   public MarcaModel saveMarca(@RequestBody MarcaModel marca){
    return marcaService.save(marca);
   }
   @GetMapping( path = "/{id}")
    public Optional<MarcaModel> getById(@PathVariable("id") Long id) {
        return this.marcaService.findById(id);
    }

    @GetMapping("/query")
    public ArrayList<MarcaModel> getMarcaByEmpresaId(@RequestParam("empresa") Long empresaId){
        return this.marcaService.findByEmpresaId(empresaId);
    }

    @DeleteMapping( path = "/{id}")
    public String deleteMarcaById(@PathVariable("id") Long id){
        boolean ok = this.marcaService.deleteById(id);
        if (ok){
            return "Se eliminó el marca con id " + id;
        }else{
            return "No pudo eliminar el marca con id" + id;
        }
    }




}
