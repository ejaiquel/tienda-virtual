package com.prueba.tienda.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.tienda.models.BodegaModel;

@Repository
public interface BodegaRepository extends CrudRepository<BodegaModel, Long> {
    public abstract ArrayList<BodegaModel> findByEmpresaId(Long empresaId);    
}
