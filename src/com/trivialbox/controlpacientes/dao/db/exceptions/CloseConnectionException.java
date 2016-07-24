package com.trivialbox.controlpacientes.dao.db.exceptions;

public class CloseConnectionException extends RuntimeException {

    public CloseConnectionException() {
        super("Error al cerrar la conexión.");
    }
    
}
