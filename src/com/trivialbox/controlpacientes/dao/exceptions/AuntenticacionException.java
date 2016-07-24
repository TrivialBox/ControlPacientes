package com.trivialbox.controlpacientes.dao.exceptions;

public class AuntenticacionException extends RuntimeException {

    public AuntenticacionException() {
        super("Usuario y contraseña no coinciden.");
    }
    
}
