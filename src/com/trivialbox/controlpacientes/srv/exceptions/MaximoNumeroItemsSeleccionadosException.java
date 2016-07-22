package com.trivialbox.controlpacientes.srv.exceptions;

public class MaximoNumeroItemsSeleccionadosException extends RuntimeException {

    public MaximoNumeroItemsSeleccionadosException() {
        super("Número máximo de selecciones.");
    }
    
}
