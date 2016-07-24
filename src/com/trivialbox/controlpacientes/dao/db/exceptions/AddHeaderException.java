package com.trivialbox.controlpacientes.dao.db.exceptions;

import java.sql.SQLException;

public class AddHeaderException extends RuntimeException {

    public AddHeaderException(SQLException ex) {
        super("Error al agregar encabezado.");
    }
    
}
