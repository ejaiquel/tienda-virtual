package com.prueba.tienda.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tienda.models.SubCategoriaModel;
import com.prueba.tienda.repositories.SubCategoriaRepository;

@Service
public class SubCategoriaService {
    
    @Autowired
    SubCategoriaRepository subCategoriaRepository;

    public ArrayList<SubCategoriaModel> getAll(){
        return (ArrayList<SubCategoriaModel>) subCategoriaRepository.findAll();
    }

    public SubCategoriaModel save(SubCategoriaModel subCategoria){
        return subCategoriaRepository.save(subCategoria);
    }

    public Optional<SubCategoriaModel> findById(Long id){
        return subCategoriaRepository.findById(id);
    }
    
    public ArrayList<SubCategoriaModel> findByCategoriaId(Long empresaId){
        return (ArrayList<SubCategoriaModel>) subCategoriaRepository.findByCategoriaId(empresaId);        
    }

    public boolean deleteById(long id){
        try {
            subCategoriaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


}
