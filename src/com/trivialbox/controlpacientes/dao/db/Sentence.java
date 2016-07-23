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
    
    private static ArrayList<ObjectField> getValues(Object o, ArrayList<String> excludes) {
        String[] aux = new String[] {"String", "Boolean", "Integer", "Double"};
        ArrayList<String> basicTypes = new ArrayList<>(Arrays.asList(aux));
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(o);
                String value = obj == null ? "" : obj.toString();
                if (basicTypes.contains(field.getType().getSimpleName()))
                    fields.add(new ObjectField(field.getName(), value));
                else if (obj != null)
                    fields.addAll(getValues(obj, excludes));
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            // Todo esta bien
        }
        return fields;
    }
    
}
