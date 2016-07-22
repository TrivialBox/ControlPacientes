package com.trivialbox.controlpacientes.srv.exceptions;

public class SeleccionNoValidaException extends RuntimeException {

    public SeleccionNoValidaException(String opcion) {
        super("Opcion: " + opcion + " no es una opción válida.");
    }
    
}
