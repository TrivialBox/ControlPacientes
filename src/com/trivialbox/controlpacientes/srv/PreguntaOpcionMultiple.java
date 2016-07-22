package com.trivialbox.controlpacientes.srv;

import java.util.ArrayList;
import java.util.List;

public class PreguntaOpcionMultiple extends Pregunta<String, List<Opcion>> {
    
    private final Opciones opciones;

    public PreguntaOpcionMultiple(int id, String titulo, boolean esOpcional, int numMaxSelecciones, boolean tieneCampoAdicional) {
        super(id, titulo, esOpcional);
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
