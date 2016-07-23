package com.trivialbox.controlpacientes.srv.objetos;

public class Administrador extends Persona {
   
    private String fechaUltimoAcceso;
    
    public Administrador(String idPersona, String clave, String nombre, String direccion, String telefono, String sexo) {
        super(idPersona, clave, nombre, direccion, telefono, sexo);
    }
    
    public void registrarAcceso() {
        // TODO implementar método para convertir calendar en fecha completa (dd-MM-yyyy hh:mm:ss)
    }
}
