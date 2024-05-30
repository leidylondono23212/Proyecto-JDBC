package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Profesores")
public class Profesor {
    @Id
    private Integer idProfesor;

    private String nombre;
    private String apellido;
    private String especialidad;
    private String certificaciones;
    private String correoElectronico;
}
