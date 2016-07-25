package com.trivialbox.controlpacientes.dao.exceptions;

public class PreguntaNoExistenteException extends RuntimeException {

    public PreguntaNoExistenteException(String nombreEncuesta, int idPregunta) {
        super("No existe una pregunta de id: " + idPregunta + " en la encuesta: " + nombreEncuesta);
    }
    
}
