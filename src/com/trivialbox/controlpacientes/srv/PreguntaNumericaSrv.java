
package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.EncuestasDAO;
import com.trivialbox.controlpacientes.dao.PreguntasDAO;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaNumerica;
import com.trivialbox.controlpacientes.srv.objetos.Rango;



public class PreguntaNumericaSrv {
    public void agregarPregunta(String tituloPregunta, int limiteSuperior, int limiteInferior, boolean esOpcional,
            boolean esIntervaloDiscreto, String nombreEncuesta){
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        
        Rango rango = new Rango(limiteInferior, limiteSuperior, esIntervaloDiscreto);
        
        PreguntaNumerica preguntaNumerica = new PreguntaNumerica(0, idEncuesta, tituloPregunta, esOpcional, rango);
        
        PreguntasDAO.getInstance().add(preguntaNumerica);
        
    }
}
