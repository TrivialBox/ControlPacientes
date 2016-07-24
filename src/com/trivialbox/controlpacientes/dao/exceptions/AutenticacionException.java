package com.trivialbox.controlpacientes.dao.exceptions;

public class AutenticacionException extends RuntimeException {

    public AutenticacionException() {
        super("Usuario y contraseña no coinciden.");
    }
    
}
