package com.trivialbox.controlpacientes.srv.exceptions;

/**
 *
 * @author root
 */
public class PreguntaHoraNoRespondidaException extends RuntimeException {

    public PreguntaHoraNoRespondidaException(int id) {
        super("En la pregunta con id: "+ id +" no ha sido respondida (HORA)");
    }
    
}
