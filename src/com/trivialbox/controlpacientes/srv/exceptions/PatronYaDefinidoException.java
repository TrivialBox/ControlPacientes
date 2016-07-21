package com.trivialbox.controlpacientes.srv.exceptions;

public class PatronYaDefinidoException extends RuntimeException {

    public PatronYaDefinidoException() {
        super("Patron ya definido.");
    }
    
}
