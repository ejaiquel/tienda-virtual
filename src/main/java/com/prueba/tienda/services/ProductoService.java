package com.prueba.tienda.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tienda.models.ProductoModel;
import com.prueba.tienda.repositories.ProductoRepository;

@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;

    public ArrayList<ProductoModel> getAll(){
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }

    public ProductoModel save(ProductoModel producto){
        return productoRepository.save(producto);
    }

    public Optional<ProductoModel> findById(Long id){
        return productoRepository.findById(id);
    }
    
    public ArrayList<ProductoModel> query(Long empresaId, long marcaId, long subCategoriaId){
        return (ArrayList<ProductoModel>) productoRepository.findByEmpresaIdAndMarcaIdAndSubCategoriaId(empresaId, marcaId, subCategoriaId);        
    }

    public boolean deleteById(long id){
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
