package com.trivialbox.controlpacientes.srv.exceptions;

public class NumeroMaximoDeCaracteresException extends RuntimeException {

    public NumeroMaximoDeCaracteresException(int length, int numMax) {
        super("Número de caracteres mayor al permito. " + length + "/" + numMax);
    }
    
}
