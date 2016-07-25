package com.trivialbox.controlpacientes.srv.encuestaSrv;

import com.trivialbox.controlpacientes.dao.EncuestasDAO;
import com.trivialbox.controlpacientes.srv.objetos.Encuesta;
import java.util.List;


public class EncuestaSrv {
    
    
    public void agregarEncuesta(String nombreEncuesta, String descripcion){
        Encuesta encuesta = new Encuesta(0, nombreEncuesta);
        encuesta.setDescripcion(descripcion);
        EncuestasDAO.getInstance().add(encuesta);
    }
    
    public List<Encuesta> obtenerEncuestas(){
        return EncuestasDAO.getInstance().get();
    }
}
