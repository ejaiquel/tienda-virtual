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

import com.prueba.tienda.models.ProductoModel;
import com.prueba.tienda.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
   @Autowired
   ProductoService productoService;

   @GetMapping()
   public ArrayList<ProductoModel> getAll(){
    return productoService.getAll();
   }

   @PostMapping()
   public ProductoModel saveProducto(@RequestBody ProductoModel Producto){
    return productoService.save(Producto);
   }
   @GetMapping( path = "/{id}")
    public Optional<ProductoModel> getById(@PathVariable("id") Long id) {
        return this.productoService.findById(id);
    }

    @GetMapping("/query")
    public ArrayList<ProductoModel> getProductoByEmpresaId(
        @RequestParam("empresa") Long empresaId,
        @RequestParam("marca") Long marcaId, 
        @RequestParam("subcategoria") Long subCategoriaId
    ){
        return this.productoService.query(empresaId,marcaId,subCategoriaId);
    }

    @DeleteMapping( path = "/{id}")
    public String deleteProductoById(@PathVariable("id") Long id){
        boolean ok = this.productoService.deleteById(id);
        if (ok){
            return "Se eliminó el Producto con id " + id;
        }else{
            return "No pudo eliminar el Producto con id" + id;
        }
    }
}
