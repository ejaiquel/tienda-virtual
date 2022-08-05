package com.prueba.tienda.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tienda.models.CategoriaModel;
import com.prueba.tienda.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    public ArrayList<CategoriaModel> getAll(){
        return (ArrayList<CategoriaModel>) categoriaRepository.findAll();
    }

    public CategoriaModel save(CategoriaModel categoria){
        return categoriaRepository.save(categoria);
    }

    public Optional<CategoriaModel> findById(Long id){
        return categoriaRepository.findById(id);
    }
    
    public ArrayList<CategoriaModel> findByEmpresaId(Long empresaId){
        return (ArrayList<CategoriaModel>) categoriaRepository.findByEmpresaId(empresaId);        
    }

    public boolean deleteById(long id){
        try {
            categoriaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


}
