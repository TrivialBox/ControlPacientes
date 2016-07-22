package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.srv.exceptions.PreguntaHoraNoRespondidaException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author root
 */
public class PreguntaHora extends Pregunta<Calendar, String>{
    private final Calendar respuestaCalendar;
    private String respuestaFormatoDB;

    public PreguntaHora(int id, String titulo, boolean esOpcional, Calendar calendarHora) {
        super(id, titulo, esOpcional);
        this.respuestaCalendar = calendarHora;
    }

    @Override
    public void responder(Calendar respuesta) {
        if(calendarioEsNulo(respuestaCalendar))
            throw new PreguntaHoraNoRespondidaException(getId());
        convertirFormatoDB(respuesta);
    }

    @Override
    protected String respuesta() {
        return this.respuestaFormatoDB;
    }

    @Override
    protected String getRespuestaPorDefecto() {
        return "";
    }

    @Override
    protected boolean preguntaRespondida() {
        return this.respuestaFormatoDB != null;
    }
    private boolean calendarioEsNulo(Calendar calendario){
        return calendario == null;
    }

    private void convertirFormatoDB(Calendar respuesta) {
        SimpleDateFormat formato = new SimpleDateFormat("H:mm:ss");
        this.respuestaFormatoDB = formato.format(respuesta.getTime());
        
    }
}
