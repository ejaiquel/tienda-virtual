package com.prueba.tienda.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tienda.models.MarcaModel;
import com.prueba.tienda.repositories.MarcaRepository;

@Service
public class MarcaService {
    
    @Autowired
    MarcaRepository marcaRepository;

    public ArrayList<MarcaModel> getAll(){
        return (ArrayList<MarcaModel>) marcaRepository.findAll();
    }

    public MarcaModel save(MarcaModel marca){
        return marcaRepository.save(marca);
    }

    public Optional<MarcaModel> findById(Long id){
        return marcaRepository.findById(id);
    }
    
    public ArrayList<MarcaModel> findByEmpresaId(Long empresaId){
        return (ArrayList<MarcaModel>) marcaRepository.findByEmpresaId(empresaId);        
    }

    public boolean deleteById(long id){
        try {
            marcaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


}
