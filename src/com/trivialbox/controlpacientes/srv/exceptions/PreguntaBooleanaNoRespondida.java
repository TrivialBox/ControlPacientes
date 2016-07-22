package com.trivialbox.controlpacientes.srv.exceptions;


/**
 *
 * @author root
 */
public class PreguntaBooleanaNoRespondida extends RuntimeException {

    public PreguntaBooleanaNoRespondida(int id) {
        super("La pregunta de id: "+ id + " no ha sido respondida");
    }
    
}
