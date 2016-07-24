package com.trivialbox.controlpacientes.dao.db.exceptions;

import java.sql.SQLException;

public class AddBodyException extends RuntimeException {

    public AddBodyException(SQLException ex) {
        super("Error al agregar filas.");
    }
    
}
