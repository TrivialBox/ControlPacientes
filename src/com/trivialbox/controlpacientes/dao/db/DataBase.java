package com.trivialbox.controlpacientes.dao.db;

import com.trivialbox.controlpacientes.dao.db.exceptions.AddBodyException;
import com.trivialbox.controlpacientes.dao.db.exceptions.AddHeaderException;
import com.trivialbox.controlpacientes.dao.db.exceptions.ConnectionException;
import com.trivialbox.controlpacientes.dao.db.exceptions.CloseConnectionException;
import com.trivialbox.controlpacientes.dao.db.exceptions.QueryException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    
    public TablaDB functionSum(String tableName, String colName) {
        String query = Sentence.functionSum(tableName, colName);
        TablaDB result = consult(query);
        return result;
    }
    
    public TablaDB functionAverage(String tableName, String colName) {
        String query = Sentence.functionAverage(tableName, colName);
        TablaDB result = consult(query);
        return result;
    }
    
    public TablaDB functionMax(String tableName, String colName) {
        String query = Sentence.functionMax(tableName, colName);
        TablaDB result = consult(query);
        return result;
    }
    
    public TablaDB functionMin(String tableName, String colName) {
        String query = Sentence.functionMin(tableName, colName);
        TablaDB result = consult(query);
        return result;
    }
    
    public TablaDB functionCount(String tableName, String colName) {
        String query = Sentence.functionCount(tableName, colName);
        TablaDB result = consult(query);
        return result;
    }
    
    public TablaDB getAll(String tableName) {
        String query = Sentence.selectAll(tableName);
        TablaDB datos;
        datos = consult(query);
        return datos;
    }
    
    public ArrayList<String> getHeader(String tableName) {
        ArrayList<String> fields = new ArrayList<>();
        String query = Sentence.allFields(tableName);
        TablaDB result = consult(query);
        for (RowDB r : result)
            fields.add(r.getField(0));
        return fields;
    }
    
    public TablaDB search(String tableName, String subString) {
        ArrayList<String> fields = getHeader(tableName);
        String query = Sentence.search(tableName, fields, subString);
        TablaDB result = this.consult(query);
        return result;
    }
    
    public TablaDB lessThan(String tableName, String colName, String value) {
        String query = Sentence.lessThan(tableName, colName, value);
        TablaDB result = consult(query);
        return result;
    }

    public TablaDB greaterThan(String tableName, String colName, String value) {
        String query = Sentence.greaterThan(tableName, colName, value);
        TablaDB result = consult(query);
        return result;
    }

    public TablaDB equalTo(String tableName, String colName, String value) {
        String query = Sentence.equalTo(tableName, colName, value);
        TablaDB result = consult(query);
        return result;
    }

    public TablaDB unequalTo(String tableName, String colName, String value) {
        String query = Sentence.unequealTo(tableName, colName, value);
        TablaDB result = consult(query);
        return result;
    }
    
    public TablaDB consult(String query) {
        TablaDB datos;
        try {
            stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            datos = new TablaDB(getHeader(result));
            addBody(datos, result);
            return datos;
        } catch (SQLException ex) {
            throw new QueryException(query, ex);
        } finally {
            closeConnection();
        }
    }
    
    public TablaDB select(List<String> tablesName, List<ObjectField> fields) {
        String query = Sentence.select(tablesName, fields);
        TablaDB result = consult(query);
        return result;
    }
    
    public TablaDB selectAll(List<String> tablesName, String field) {
        String query = Sentence.selectAll(tablesName, field);
        TablaDB result = consult(query);
        return result;
    }

    private ArrayList<String> getHeader(ResultSet result) {
        try {
            ArrayList<String> row = new ArrayList<>();
            int numCols = result.getMetaData().getColumnCount();
            for (int i = 0; i < numCols; i++)
                row.add(result.getMetaData().getColumnName(i + 1));
            return row;
        } catch (SQLException ex) {
            throw new AddHeaderException(ex);
        }
    }
    
    private void addBody(TablaDB datos, ResultSet result) {
        try {
            int numCols = result.getMetaData().getColumnCount();
            RowDB row;
            while (result.next()) {
                row = new RowDB();
                for (int i = 0; i < numCols; i++)
                    row.addField(result.getString(i + 1));
                datos.addRow(row);
            }
        } catch (SQLException ex) {
            throw new AddBodyException(ex);
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
