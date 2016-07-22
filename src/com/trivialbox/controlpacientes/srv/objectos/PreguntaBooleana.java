package com.trivialbox.controlpacientes.srv.objectos;

import com.trivialbox.controlpacientes.srv.exceptions.PreguntaBooleanaNoRespondida;


public class PreguntaBooleana extends Pregunta<Boolean, Boolean>{
    private Boolean respuesta = null;
    
    public PreguntaBooleana(int id, String titulo, boolean esOpcional){
        super(id, titulo, esOpcional);
    }

    @Override
    public void responder(Boolean respuesta) {
        if(esNula(respuesta))
            throw new PreguntaBooleanaNoRespondida(getId());
        this.respuesta = respuesta;
    }

    @Override
    protected Boolean respuesta() {
        return this.respuesta;
    }

    @Override
    protected Boolean getRespuestaPorDefecto() {
        return null;
    }

    @Override
    protected boolean preguntaRespondida() {
        return this.respuesta != null;
    }

    private boolean  esNula(Boolean respuesta){
        return respuesta == null;
    }
        
}
