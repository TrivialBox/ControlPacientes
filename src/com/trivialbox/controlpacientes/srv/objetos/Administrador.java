package com.trivialbox.controlpacientes.srv.objetos;

public class Administrador extends Persona {
   
    private String fechaUltimoAcceso;
    
    public Administrador(String idPersona, String clave, String nombre, String direccion, String telefono, String sexo) {
        super(idPersona, clave, nombre, direccion, telefono, sexo);
    }

    public String getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    public void setFechaUltimoAcceso(String fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }
    
}
