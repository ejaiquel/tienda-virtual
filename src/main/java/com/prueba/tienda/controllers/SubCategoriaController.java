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

import com.prueba.tienda.models.SubCategoriaModel;
import com.prueba.tienda.services.SubCategoriaService;

@RestController
@RequestMapping("/subcategorias")
public class SubCategoriaController {
   @Autowired
   SubCategoriaService subCategoriaService;

   @GetMapping()
   public ArrayList<SubCategoriaModel> getAll(){
    return subCategoriaService.getAll();
   }

   @PostMapping()
   public SubCategoriaModel saveCategoria(@RequestBody SubCategoriaModel categoria){
    return subCategoriaService.save(categoria);
   }
   @GetMapping( path = "/{id}")
    public Optional<SubCategoriaModel> getById(@PathVariable("id") Long id) {
        return this.subCategoriaService.findById(id);
    }

    @GetMapping("/query")
    public ArrayList<SubCategoriaModel> getCategoriaByCategoriaId(@RequestParam("categoria") Long categoriaId){
        return this.subCategoriaService.findByCategoriaId(categoriaId);
    }

    @DeleteMapping( path = "/{id}")
    public String deleteCategoriaById(@PathVariable("id") Long id){
        boolean ok = this.subCategoriaService.deleteById(id);
        if (ok){
            return "Se eliminó la Sub Categoria con id " + id;
        }else{
            return "No pudo eliminar la Sub Categoria con id " + id;
        }
    }
}
