package com.trivialbox.controlpacientes.srv.exceptions;

public class PreguntaNoRespondidaException extends RuntimeException {

    public PreguntaNoRespondidaException(int id) {
        super("Pregunta de id: " + id + " no ha sido respondida.");
    }
    
}
