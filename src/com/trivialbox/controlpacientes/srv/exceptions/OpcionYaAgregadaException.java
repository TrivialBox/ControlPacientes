package com.trivialbox.controlpacientes.srv.exceptions;

public class OpcionYaAgregadaException extends RuntimeException {

    public OpcionYaAgregadaException(String opcion) {
        super("Opcion: " + opcion + " ya ha sido agregada.");
    }
    
}
