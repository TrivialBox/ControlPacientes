package com.trivialbox.controlpacientes.dao.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Sentence {
    
    /*
    public static String insert(Object o) {
        Set<Map.Entry<String, String>> object_values = getValues(o).entrySet();
        StringJoiner fields = new StringJoiner(",");
        StringJoiner values = new StringJoiner(",");
        
        for (Map.Entry<String, String> entry : object_values) {
            fields.add(entry.getKey());
            values.add(entry.getValue());
        }
        
        String query;
        query = "INSERT INTO " + getName(o) + " " +
                "(" + fields.toString() + ")" + " " +
                "VALUES(" + values.toString() + ")";
        return query;
    }
    */
    
    private static String getName(Object o) {
        return o.getClass().getName();
    }
    
    private static ArrayList<ObjectField> getValues(Object o, ArrayList<String> excludes) {
        String[] aux = new String[] {"String", "Boolean", "Integer", "Double"};
        ArrayList<String> basicTypes = new ArrayList<>(Arrays.asList(aux));
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(o);
                String type = field.getType().getSimpleName();
                String name = field.getName();
                String value = obj == null ? "" : obj.toString();
                
                if (excludes.contains(name))
                    continue;
                
                if (basicTypes.contains(type))
                    fields.add(new ObjectField(name, value));
                else if (obj != null)
                    fields.addAll(getValues(obj, excludes));
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            // Todo esta bien
        }
        return fields;
    }
    
    public static String selectAll(String tableName) {
        String query;
        query = "SELECT * FROM " + tableName;
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
    
}
