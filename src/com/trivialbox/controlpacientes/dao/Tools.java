package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.db.ObjectField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class Tools {
    public static String getName(Object o) {
        return o.getClass().getName();
    }
    
    public static ArrayList<ObjectField> getValues(Object o, ArrayList<String> excludes) {
        ArrayList<ObjectField> fields = new ArrayList<>();
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(o);
                String type = field.getType().getSimpleName();
                String name = field.getName();
                String value = obj == null ? "" : obj.toString();
                
                if (!excludes.contains(name)) {
                    if (getBasicTypes().contains(type))
                        fields.add(new ObjectField(name, value));
                    else if (obj != null)
                        fields.addAll(getValues(obj, excludes));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            // Todo esta bien
        }
        return fields;
    }
    
    private static ArrayList<String> getBasicTypes() {
        String[] aux = new String[] {"String", "Boolean", "Integer", "Double"};
        return new ArrayList<>(Arrays.asList(aux));
    }
}
