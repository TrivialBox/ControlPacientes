package com.trivialbox.controlpacientes.dao.db;

public class ConnectionException extends RuntimeException {

    public ConnectionException(LoginDB login) {
        super("Error al conectarse a la base de datos.");
    }
    
}
