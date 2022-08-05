package com.prueba.tienda.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.tienda.models.ProductoModel;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long> {
    public abstract ArrayList<ProductoModel> findByEmpresaIdAndMarcaIdAndSubCategoriaId(Long empresaId, Long marcaId, Long subCategoriaId);        
}
