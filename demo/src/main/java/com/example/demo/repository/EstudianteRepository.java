package com.example.demo.repository;


import com.example.demo.model.Estudiante;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante, Integer> {
    @Query("SELECT e.* FROM estudiantes e INNER JOIN estudiantes_cursos ec ON e.ID_Estudiante = ec.ID_Estudiante WHERE ec.ID_Curso = :id")
    Set<Estudiante> findByCurso(@Param("id") Integer id);
}
