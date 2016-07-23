package com.trivialbox.controlpacientes.srv.objectos;

public final class Encuesta {
    private final Integer id;
    private String nombre;
    private String descripcion = "";
    
    public Encuesta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
