package com.banana.proyectostareas.service;

import com.banana.proyectostareas.exception.ProyectoNotfoundException;
import com.banana.proyectostareas.exception.TareaNotfoundException;
import com.banana.proyectostareas.model.Proyecto;
import com.banana.proyectostareas.model.Tarea;

import java.util.List;

public interface ProyectoTareaService {

    public Proyecto crearProyecto(Proyecto proyecto) throws RuntimeException;

    public Proyecto anadeTareaAProyecto(Long idProyecto, Tarea tarea) throws ProyectoNotfoundException, RuntimeException;

    public List<Proyecto> obtenerProyectos() throws ProyectoNotfoundException, RuntimeException;

    public List<Tarea> obtenerTareasDelProyecto(Long idProyecto) throws ProyectoNotfoundException, RuntimeException;

    public Tarea marcarTareaHecha(Long idTarea) throws TareaNotfoundException, RuntimeException;

}
