package com.example.demo.repository;

import com.example.demo.model.Especializacion;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EspecializacionRepository extends CrudRepository<Especializacion, Integer> {


    @Query("SELECT * FROM especializaciones WHERE ID_Profesor = :id")
        Set<Especializacion> findByProfesor(@Param("id") Integer id);

}
