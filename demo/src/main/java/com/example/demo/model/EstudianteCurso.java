package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Estudiantes_Cursos")
public class EstudianteCurso {
    @Id
    private Integer idEstudianteCurso;
    private Integer idEstudiante;
    private Integer idCurso;
}
