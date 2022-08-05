package com.prueba.tienda.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.tienda.models.MarcaModel;

@Repository
public interface MarcaRepository extends CrudRepository<MarcaModel, Long> {
    public abstract ArrayList<MarcaModel> findByEmpresaId(Long empresaId);    
}
