package com.trivialbox.controlpacientes.srv.objetos;

import java.util.ArrayList;
import java.util.List;

public class PreguntaOpcionMultiple extends Pregunta<String, List<Opcion>> {
    
    private final Opciones opciones;

    /**
     * 
     * @param id
     * @param idEncuesta
     * @param titulo
     * @param esOpcional
     * @param numMaxSelecciones 0 si no tiene límite.
     * @param tieneCampoAdicional 
     */
    public PreguntaOpcionMultiple(int id, int idEncuesta, String titulo, boolean esOpcional, int numMaxSelecciones, boolean tieneCampoAdicional) {
        super(id, idEncuesta, titulo, esOpcional);
        opciones = new Opciones(numMaxSelecciones, tieneCampoAdicional);
    }
    
    public int getNumMaxSelecciones() {
        return opciones.getNumMaxSelecciones();
    }
    
    public void addOpcion(String opcion) {
        opciones.addOpcion(opcion);
    }

    @Override
    public void responder(String respuesta) {
        opciones.select(respuesta);
    }

    @Override
    protected List<Opcion> respuesta() {
        return opciones.getSeleccionados();
    }

    @Override
    protected List<Opcion> getRespuestaPorDefecto() {
        return new ArrayList<>();
    }

    @Override
    protected boolean preguntaRespondida() {
        return opciones.getSeleccionados().size() > 0;
    }
}
