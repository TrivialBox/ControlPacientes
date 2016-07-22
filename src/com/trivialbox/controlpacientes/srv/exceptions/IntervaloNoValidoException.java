package com.trivialbox.controlpacientes.srv.exceptions;

import com.trivialbox.controlpacientes.srv.objectos.Rango;

public class IntervaloNoValidoException extends RuntimeException {

    public IntervaloNoValidoException(Double respuesta, Rango rango) {
        super("El número no está dentro de un intervalo válido.");
    }
    
}
