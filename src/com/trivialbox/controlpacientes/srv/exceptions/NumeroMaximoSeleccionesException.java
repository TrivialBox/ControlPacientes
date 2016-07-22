package com.trivialbox.controlpacientes.srv.exceptions;

public class NumeroMaximoSeleccionesException extends RuntimeException {

    public NumeroMaximoSeleccionesException() {
        super("Número de selcciones permitidas sobrepasado.");
    }
    
}
