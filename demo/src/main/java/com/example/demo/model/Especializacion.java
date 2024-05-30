package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Especializaciones")
public class Especializacion {
    @Id
    private Integer idEspecializacion;

    private Integer idProfesor;

    private String nombreEspecializacion;
    private String nivelEspecializacion;
    private String institucion;

    @Transient
    private Profesor profesor;
}
