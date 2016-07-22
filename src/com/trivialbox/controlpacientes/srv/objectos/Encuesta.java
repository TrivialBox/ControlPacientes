package com.trivialbox.controlpacientes.srv.objectos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class Encuesta {
    private final int id;
    private String nombre;
    private final Map<Integer, Pregunta> preguntas;
    private String descripcion = "";

    public Encuesta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.preguntas = new HashMap<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Pregunta get(Integer id) {
        return preguntas.get(id);
    }

    public Iterator<Pregunta> getPreguntas() {
        return preguntas.values().iterator();
    }
    
    public void add(Pregunta pregunta) {
        preguntas.put(pregunta.getId(), pregunta);
    }
    
    public void del(Integer id) {
        preguntas.remove(id);
    }
    
}
