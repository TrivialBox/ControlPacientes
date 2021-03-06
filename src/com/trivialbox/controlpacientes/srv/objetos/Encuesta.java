package com.trivialbox.controlpacientes.srv.objetos;

public final class Encuesta {
    private final Integer idEncuesta;
    private String nombre;
    private String descripcion = "";
    
    public Encuesta(int id, String nombre) {
        this.idEncuesta = id;
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return idEncuesta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
}
