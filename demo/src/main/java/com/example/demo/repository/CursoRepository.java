package com.example.demo.repository;

import com.example.demo.model.Curso;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Integer> {
    @Query("SELECT c.* FROM cursos c INNER JOIN estudiantes_cursos ec ON c.ID_Curso = ec.ID_Curso WHERE ec.ID_Estudiante = :id")
    Set<Curso> findByEstudiante(@Param("id") Integer id);
}
