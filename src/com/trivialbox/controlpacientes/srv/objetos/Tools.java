package com.trivialbox.controlpacientes.srv.objetos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tools {
    
    public static String calendarToTimeString(Calendar respuesta) {
        SimpleDateFormat formato = new SimpleDateFormat("H:mm:ss");
        return formato.format(respuesta.getTime());
    }
    
}
