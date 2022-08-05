package com.prueba.tienda.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tienda.models.BodegaModel;
import com.prueba.tienda.repositories.BodegaRepository;

@Service
public class BodegaService {
    
    @Autowired
    BodegaRepository bodegaRepository;

    public ArrayList<BodegaModel> getAll(){
        return (ArrayList<BodegaModel>) bodegaRepository.findAll();
    }

    public BodegaModel save(BodegaModel bodega){
        return bodegaRepository.save(bodega);
    }

    public Optional<BodegaModel> findById(Long id){
        return bodegaRepository.findById(id);
    }
    
    public ArrayList<BodegaModel> findByEmpresaId(Long empresaId){
        return (ArrayList<BodegaModel>) bodegaRepository.findByEmpresaId(empresaId);        
    }

    public boolean deleteById(long id){
        try {
            bodegaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


}
