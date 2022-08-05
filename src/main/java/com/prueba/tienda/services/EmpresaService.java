package com.prueba.tienda.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tienda.models.EmpresaModel;
import com.prueba.tienda.repositories.EmpresaRepository;

@Service
public class EmpresaService {
    
    @Autowired
    EmpresaRepository empresaRepository;

    public ArrayList<EmpresaModel> getAll(){
        return (ArrayList<EmpresaModel>) empresaRepository.findAll();
    }

    public EmpresaModel save(EmpresaModel empresa){
        return empresaRepository.save(empresa);
    }

    public Optional<EmpresaModel> findById(Long id){
        return empresaRepository.findById(id);
    }
    
    // public ArrayList<EmpresaModel> findByEmail(String email){
    //     return (ArrayList<EmpresaModel>) EmpresaRepository.findByEmail(email);        
    // }

    public boolean deleteById(long id){
        try {
            empresaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }


}
