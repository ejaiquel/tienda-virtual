package com.prueba.tienda.repositories;

//import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.tienda.models.EmpresaModel;

@Repository
public interface EmpresaRepository extends CrudRepository<EmpresaModel, Long> {
    //public abstract ArrayList<EmpresaModel> findByEmail(String email);    
}
