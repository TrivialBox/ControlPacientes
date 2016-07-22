package com.trivialbox.controlpacientes.srv.exceptions;

import com.trivialbox.controlpacientes.srv.objectos.Rango;

public class FueraDeRangoException extends RuntimeException {

    public FueraDeRangoException(Double respuesta, Rango rango) {
        super("El número "
                + respuesta
                + " se encuentra fuera del rango "
                + rango.getLimiteInferior()
                + " : "
                + rango.getLimiteSuperior()
        );
    }
    
}
