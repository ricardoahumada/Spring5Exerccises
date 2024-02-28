package com.banana.proyectostareas.persistence;

import com.banana.proyectostareas.model.Proyecto;
import com.banana.proyectostareas.model.Tarea;

import java.util.List;

public interface ProyectoJPARepository {

    public List<Proyecto> findAll() throws RuntimeException;

    public Proyecto save(Proyecto proyecto) throws RuntimeException;

    public Proyecto update(Proyecto proyecto) throws RuntimeException;

}
