package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Estudiantes")
public class Estudiante {
    @Id
    private Integer idEstudiante;

    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String telefono;
    private String direccion;
}
