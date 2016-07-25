
package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.EncuestasDAO;
import com.trivialbox.controlpacientes.dao.PreguntasDAO;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaTextual;
import com.trivialbox.controlpacientes.srv.objetos.RegexPatterns;

public class PreguntaTextualSrv {
    public void agregarPreguntaTextual(String nombreEncuesta, String titulo, boolean esOpcional, int numMaxCaracteres, String tipoPatron ){
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        
        PreguntaTextual preguntaTextual = new PreguntaTextual(0, idEncuesta, titulo, esOpcional, numMaxCaracteres);
        preguntaTextual.setPatron(obtenerPatron(tipoPatron));
        PreguntasDAO.getInstance().add(preguntaTextual);
    }
    
    private String obtenerPatron(String tipoPatron){
        switch(tipoPatron){
            case "Número de celular":
                return RegexPatterns.numeroCelular();
            case "Solo números":
                return RegexPatterns.soloNumeros();
            case "Correo electrónico":
                return RegexPatterns.email();
            case "Dirección url":
                return RegexPatterns.URL();
        }
        return null;
    }
    

}
