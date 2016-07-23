package com.trivialbox.controlpacientes.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    
    private Connection connection;
    private Statement stmt;
    private String query;

    public DataBase(LoginDB login) {
        conectar(login);
    }
    
    private void conectar(LoginDB login) {
        try {
            Connection conn;
            conn = DriverManager.getConnection(login.getDbUrl(), login.getProperties());
            this.connection = conn;
        } catch (SQLException ex) {
            throw new ConnectionException(login);
        }
    }
    
}
