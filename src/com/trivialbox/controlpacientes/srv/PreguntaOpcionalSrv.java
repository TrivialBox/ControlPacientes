package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.EncuestasDAO;
import com.trivialbox.controlpacientes.dao.PreguntasDAO;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaOpcionMultiple;
import java.util.ArrayList;
import java.util.function.Consumer;



public class PreguntaOpcionalSrv {
    public void agregarPregunta(String tituloPregunta, boolean esOpcional, int numMaxSelecciones, 
                                boolean tieneCampoAdicional, ArrayList<String> opciones, String nombreEncuesta){
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        PreguntaOpcionMultiple preguntaOpcionMultiple = new PreguntaOpcionMultiple(0, idEncuesta, tituloPregunta, esOpcional, 
                                                                                    numMaxSelecciones, tieneCampoAdicional);
        
        agregarOpciones(preguntaOpcionMultiple, opciones);
        
        PreguntasDAO.getInstance().add(preguntaOpcionMultiple);
        
        
    }
    private void agregarOpciones(PreguntaOpcionMultiple preguntaOpcionMultiple, ArrayList<String> opciones){
        opciones.stream().forEach(preguntaOpcionMultiple::addOpcion);
    }
}
