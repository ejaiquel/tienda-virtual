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

import com.prueba.tienda.models.BodegaModel;
import com.prueba.tienda.services.BodegaService;

@RestController
@RequestMapping("/bodegas")
public class BodegaController {
   @Autowired
   BodegaService bodegaService;

   @GetMapping()
   public ArrayList<BodegaModel> getAll(){
    return bodegaService.getAll();
   }

   @PostMapping()
   public BodegaModel saveBodega(@RequestBody BodegaModel Bodega){
    return bodegaService.save(Bodega);
   }
   @GetMapping( path = "/{id}")
    public Optional<BodegaModel> getById(@PathVariable("id") Long id) {
        return this.bodegaService.findById(id);
    }

    @GetMapping("/query")
    public ArrayList<BodegaModel> getBodegaByEmpresaId(@RequestParam("empresa") Long empresaId){
        return this.bodegaService.findByEmpresaId(empresaId);
    }

    @DeleteMapping( path = "/{id}")
    public String deleteBodegaById(@PathVariable("id") Long id){
        boolean ok = this.bodegaService.deleteById(id);
        if (ok){
            return "Se eliminó el Bodega con id " + id;
        }else{
            return "No pudo eliminar el Bodega con id" + id;
        }
    }
}
