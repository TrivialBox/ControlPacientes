package com.trivialbox.controlpacientes.srv.objetos;

import java.util.Calendar;

public class PreguntaHora extends Pregunta<Calendar, String> {
    
    private String respuesta = null;

    public PreguntaHora(int id, int idEncuesta, String titulo, boolean esOpcional) {
        super(id, idEncuesta, titulo, esOpcional);
    }

    @Override
    public void responder(Calendar respuesta) {
        this.respuesta = Tools.calendarToTimeString(respuesta);
    }

    @Override
    protected String respuesta() {
        return this.respuesta;
    }

    @Override
    protected String getRespuestaPorDefecto() {
        return "";
    }

    @Override
    protected boolean preguntaRespondida() {
        return this.respuesta != null;
    }
}
