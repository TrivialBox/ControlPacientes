package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.exceptions.EncuestaYaRespondidaException;
import com.trivialbox.controlpacientes.dao.exceptions.EncuestaNoPermitidaException;
import com.trivialbox.controlpacientes.dao.exceptions.UsuarioEncuestaNoEncontradoException;
import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Pregunta;
import java.util.ArrayList;
import java.util.List;

public class RespuestasDAO {
    private final DataBase dataBase;
    private static RespuestasDAO instance;

    private RespuestasDAO() {
        dataBase = DataBase.getInstance();
    }
    
    public static RespuestasDAO getInstance() {
        if (instance == null)
            instance = new RespuestasDAO();
        return instance;
    }
    
    /**
     * Todas las respuestas de una encuesta.
     * @param nombreEncuesta
     * @return 
     */
    public List<Pregunta> get(String nombreEncuesta) {
        return null;
    }
    
    /**
     * Todas las respuestas de una pregunta.
     * @param nombreEncuesta
     * @param idPregunta
     * @return 
     */
    public List<Pregunta> get(String nombreEncuesta, int idPregunta) {
        return null;
    }
    
    /**
     * Todas las respuestas de una persona sobre una encuesta.
     * @param nombreEncuesta
     * @param idPaciente
     * @return 
     */
    public List<Pregunta> get(String nombreEncuesta, String idPaciente) {
        return null;
    }
    
    /**
     * Responder una encuesta.
     * @param nombreEncuesta
     * @param preguntas 
     * @param idPaciente 
     */
    public void add(String nombreEncuesta, List<Pregunta> preguntas, String idPaciente) {
        puedeContestar(nombreEncuesta, idPaciente);
        
        ArrayList<String> table = new ArrayList<>();
        table.add("EncuestaPersona");
        ArrayList<String> fields = new ArrayList<>();
        
    }
    
    private int getIdEncuestaFromNombre(String nombreEncuesta) {
        int idEncuesta;
        ArrayList<String> table = new ArrayList<>();
        table.add("Pregunta");
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("nombre", nombreEncuesta));
        
        TablaDB result = dataBase.select(table, fields);
        
        idEncuesta = Integer.parseInt(result.getRow(0).getField(0));
        return idEncuesta;
    }

    private void puedeContestar(String nombreEncuesta, String idPaciente) {
        int idEncuesta = getIdEncuestaFromNombre(nombreEncuesta);
        
        ArrayList<String> table = new ArrayList<>();
        table.add("EncuestaPersona");
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idPersona", idPaciente));
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        
        TablaDB result = dataBase.select(table, fields);
        
        if (result.isEmpty())
            throw new UsuarioEncuestaNoEncontradoException();
        if (result.getRow(0).getField(3).isEmpty())
            throw new EncuestaNoPermitidaException(idPaciente);
        if (!result.getRow(0).getField(4).isEmpty())
            throw new EncuestaYaRespondidaException();
    }
}
