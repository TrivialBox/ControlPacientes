package com.trivialbox.controlpacientes.dao.db.exceptions;

import java.sql.SQLException;

public class QueryException extends RuntimeException {

    public QueryException(String query, SQLException ex) {
        super("Error al ejecutar la sentencia: " + query + " (" + ex.getMessage() + ")");
    }
    
}
