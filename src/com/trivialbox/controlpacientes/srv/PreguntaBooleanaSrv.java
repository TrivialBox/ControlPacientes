package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.EncuestasDAO;
import com.trivialbox.controlpacientes.dao.PreguntasDAO;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaBooleana;



public class PreguntaBooleanaSrv {
    public void agregarPregunta(String tituloPregunta, boolean esOpcional, String nombreEncuesta){
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        PreguntaBooleana preguntaBooleana = new PreguntaBooleana(0, idEncuesta, tituloPregunta, esOpcional);
        PreguntasDAO.getInstance().add(preguntaBooleana);
    }
}
