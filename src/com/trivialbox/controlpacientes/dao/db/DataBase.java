package com.trivialbox.controlpacientes.dao.db;

import com.trivialbox.controlpacientes.dao.db.exceptions.ConnectionException;
import com.trivialbox.controlpacientes.dao.db.exceptions.CloseConnectionException;
import com.trivialbox.controlpacientes.dao.db.exceptions.QueryException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataBase {
    
    private Connection connection;
    private Statement stmt;
    
    private static DataBase instance;
    
    public static DataBase getInstance() {
        if (instance == null)
            instance = new DataBase();
        return instance;
    }
    
    public static void deleteInstance() {
        instance = null;
    }

    private DataBase() {
    }
    
    public void conectar(LoginDB login) {
        try {
            Connection conn;
            conn = DriverManager.getConnection(login.getDbUrl(), login.getProperties());
            this.connection = conn;
        } catch (SQLException ex) {
            throw new ConnectionException(login);
        }
    }
    
    public void insert(String tableName, List<ObjectField> fields) {
        String query;
        query = Sentence.insert(tableName, fields);
        update(query);
    }
    
    public void update(String tableName, List<ObjectField> previewFields, List<ObjectField> newFields) {
        String query;
        query = Sentence.update(tableName, previewFields, newFields);
        update(query);
    }
    
    public void delete(String tableName, List<ObjectField> fields) {
        String query = Sentence.delete(tableName, fields);
        query(query);
    }
    
    private void query(String query) {
        try {
            stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException ex) {
            throw new QueryException(query, ex);
        } finally {
            closeConnection();
        }
    }
    
    private void update(String query) {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new QueryException(query, ex);
        } finally {
            closeConnection();
        }
    }
    
    private void closeConnection() {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException ex) {
            throw new CloseConnectionException();
        }
    }

}
