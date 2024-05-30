package com.example.demo.repository;

import com.example.demo.model.EstudianteCurso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCursoRepository extends CrudRepository<EstudianteCurso, Integer> {
}
