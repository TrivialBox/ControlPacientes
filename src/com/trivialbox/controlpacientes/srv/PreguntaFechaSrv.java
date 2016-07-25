package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.EncuestasDAO;
import com.trivialbox.controlpacientes.dao.PreguntasDAO;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaFecha;



public class PreguntaFechaSrv {
    public void agregarPregunta(String tituloPregunta, boolean esOpcional, String nombreEncuesta){
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        PreguntaFecha preguntaFecha = new PreguntaFecha(0, idEncuesta, tituloPregunta, esOpcional);
        PreguntasDAO.getInstance().add(preguntaFecha);
    }
}
