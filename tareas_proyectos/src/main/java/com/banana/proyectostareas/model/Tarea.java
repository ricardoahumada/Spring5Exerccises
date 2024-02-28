package com.banana.proyectostareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    private Long id;
    private String descripcion;
    private LocalDate fechaLimite;
    private Integer orden;
    private boolean completada;

}
