package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.EncuestasDAO;
import com.trivialbox.controlpacientes.dao.PreguntasDAO;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaHora;

public class PreguntaHoraSrv {
    public void agregarPregunta(String tituloPregunta, boolean esOpcional, String nombreEncuesta){
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        PreguntaHora preguntaHora = new PreguntaHora(0, idEncuesta, tituloPregunta, esOpcional);
        PreguntasDAO.getInstance().add(preguntaHora);
    }
}
