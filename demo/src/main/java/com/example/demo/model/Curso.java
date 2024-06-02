package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Table("Cursos")
public class Curso {
    @Id
    private Integer idCurso;

    private String nombreCurso;
    private String descripcion;
    private String nivel;
    private int duracion;
    private String horario;
    @Column("ID_Profesor")
    private Integer idProfesor;

    @Transient
    private Profesor profesor;
}
