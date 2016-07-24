package com.trivialbox.controlpacientes.srv.objetos;

import com.trivialbox.controlpacientes.dao.db.ObjectField;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.xml.bind.DatatypeConverter;

public class Tools {
    
    public static String calendarToTimeString(Calendar respuesta) {
        SimpleDateFormat formato = new SimpleDateFormat("H:mm:ss");
        return formato.format(respuesta.getTime());
    }
    
    public static String encryptPassword(String password) {
        MessageDigest md;
        String encryptMsg = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            encryptMsg = DatatypeConverter.printHexBinary(md.digest(password.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            // Todo ok
        }
        return encryptMsg;
    }
    
    public static String getObjectName(Object o) {
        return o.getClass().getSimpleName();
    }
    
    public static String getObjectName(Class<?> c) {
        return c.getSimpleName();
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
