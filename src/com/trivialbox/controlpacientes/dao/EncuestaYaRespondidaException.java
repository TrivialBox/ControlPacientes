package com.trivialbox.controlpacientes.dao;

public class EncuestaYaRespondidaException extends RuntimeException {

    public EncuestaYaRespondidaException() {
        super("La encuesta ya ha sido respondida.");
    }
    
}
