package com.example.demo.repository;

import com.example.demo.model.Especializacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecializacionRepository extends CrudRepository<Especializacion, Integer> {
}
