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

import com.prueba.tienda.models.CategoriaModel;
import com.prueba.tienda.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
   @Autowired
   CategoriaService categoriaService;

   @GetMapping()
   public ArrayList<CategoriaModel> getAll(){
    return categoriaService.getAll();
   }

   @PostMapping()
   public CategoriaModel saveCategoria(@RequestBody CategoriaModel categoria){
    return categoriaService.save(categoria);
   }
   @GetMapping( path = "/{id}")
    public Optional<CategoriaModel> getById(@PathVariable("id") Long id) {
        return this.categoriaService.findById(id);
    }

    @GetMapping("/query")
    public ArrayList<CategoriaModel> getCategoriaByEmpresaId(@RequestParam("empresa") Long empresaId){
        return this.categoriaService.findByEmpresaId(empresaId);
    }

    @DeleteMapping( path = "/{id}")
    public String deleteCategoriaById(@PathVariable("id") Long id){
        boolean ok = this.categoriaService.deleteById(id);
        if (ok){
            return "Se eliminó el Categoria con id " + id;
        }else{
            return "No pudo eliminar el Categoria con id" + id;
        }
    }
}
