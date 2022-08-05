package com.prueba.tienda.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.tienda.models.SubCategoriaModel;

@Repository
public interface SubCategoriaRepository extends CrudRepository<SubCategoriaModel, Long> {
    public abstract ArrayList<SubCategoriaModel> findByCategoriaId(Long categoriaId);    
}
