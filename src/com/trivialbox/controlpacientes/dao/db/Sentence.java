package com.trivialbox.controlpacientes.dao.db;

import java.util.List;
import java.util.StringJoiner;

public class Sentence {
        
    public static String selectAll(String tableName) {
        String query;
        query = "SELECT" + " "  + "*"  + " " +
                "FROM" + " " + tableName;
        return query;
    }
    
    static String allColumns(String tableName) {
        String query;
        query = "SELECT" + " " + "COLUMN_NAME" + " " +
                "FROM" + " " + "USER_TAB_COLUMNS" + " " +
                "WHERE" + " " + "TABLE_NAME" + "=" + "'" + tableName + "'";
        return query;
    }
    
    static String allTableNames() {
        String query;
        query = "SELECT" + " " + "TABLE_NAME" + " " +
                "FROM" + " " + "USER_TABLES";
        return query;
    }
    
    
    public static String functionSum(String tableName, String colName) {
        return function("SUM", tableName, colName);
    }
    
    public static String functionAverage(String tableName, String colName) {
        return function("AVG", tableName, colName);
    }
    
    public static String functionMax(String tableName, String colName) {
        return function("MAX", tableName, colName);
    }
    
    public static String functionMin(String tableName, String colName) {
        return function("MIN", tableName, colName);
    }
    
    public static String functionCount(String tableName, String colName) {
        return function("COUNT", tableName, colName);
    }
    
    private static String function(String functionName, String tableName, String colName) {
        String query;
        query = "SELECT" + " " +
                functionName + "(" + colName + ")" +
                "FROM" + " " + tableName;
        return query;
    }
    
    
    public static String lessThan(String tableName, String colName, String value) {
        String query;
        query = getCondition(tableName, colName, value, "<");
        return query;                
    }

    public static String greaterThan(String tableName, String colName, String value) {
        String query;
        query = getCondition(tableName, colName, value, ">");
        return query;                
    }

    public static String equalTo(String tableName, String colName, String value) {
        String query;
        query = getCondition(tableName, colName, value, "=");
        return query;                
    }

    public static String unequealTo(String tableName, String colName, String value) {
        String query;
        query = getCondition(tableName, colName, value, "<>");
        return query;
    }

    private static String getCondition(String tableName, String colName, String value, String operator) {
        String query;
        query = "SELECT" + " " + "*" + " " +
                "FROM" + " " + tableName + " " +
                "WHERE" + " " + colName + operator + "'" + value + "'";
        return query;
    }

    static String insert(String tableName, List<ObjectField> fields) {
        StringJoiner fieldsNames = new StringJoiner(",");
        StringJoiner fieldsValues = new StringJoiner(",");
        for (ObjectField field : fields) {
            if (!field.getValue().isEmpty()) {
                fieldsNames.add(field.getName());
                fieldsValues.add("'" + field.getValue() + "'");
            }
        }
        String query;
        query = "INSERT" + " " + "INTO" + " " + tableName + " " +
                "(" + fieldsNames.toString() + ")" + " " +
                "VALUES" + "(" + fieldsValues.toString() + ")";
        return query;
    }

    static String update(String tableName, List<ObjectField> previewFields, List<ObjectField> newFields) {
        String query;
        query = "UPDATE" + " " + tableName + " " +
                "SET" + " " + getSet(newFields) + " " +
                "WHERE" + " " + getCondition(previewFields);
        return query;
    }
    
    private static String getSet(List<ObjectField> fields) {
        StringJoiner condition = new StringJoiner(", ");
        for (ObjectField field : fields)
            condition.add(field.getName() + "=" + "'" + field.getValue() + "'");
        return condition.toString();
    }

    static String delete(String tableName, List<ObjectField> fields) {
        String query;
        query = "DELETE FROM" + " " + tableName + " " +
                "WHERE" + " " + getCondition(fields); 
        return query;
    }
    
    private static String getCondition(List<ObjectField> fields) {
        StringJoiner condition = new StringJoiner(" AND ");
        for (ObjectField field : fields)
            condition.add(field.getName() + "=" + "'" + field.getValue() + "'");
        return condition.toString();
    }
    
}
