package com.banana.proyectostareas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {
    private Long id;
    private String nombre;
    private LocalDate fechaCreacion;
}
