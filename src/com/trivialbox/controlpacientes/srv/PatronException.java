package com.trivialbox.controlpacientes.srv;

public class PatronException extends RuntimeException {

    public PatronException(String respuesta) {
        super("El texto: " + respuesta + " no es válido.");
    }
    
}
