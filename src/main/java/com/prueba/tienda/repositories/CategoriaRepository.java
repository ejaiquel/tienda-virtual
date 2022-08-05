package com.prueba.tienda.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.tienda.models.CategoriaModel;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaModel, Long> {
    public abstract ArrayList<CategoriaModel> findByEmpresaId(Long empresaId);    
}
