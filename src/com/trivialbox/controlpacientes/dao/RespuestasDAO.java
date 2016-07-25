package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.exceptions.EncuestaYaRespondidaException;
import com.trivialbox.controlpacientes.dao.exceptions.EncuestaNoPermitidaException;
import com.trivialbox.controlpacientes.dao.exceptions.UsuarioEncuestaNoEncontradoException;
import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Pregunta;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaBooleana;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaFecha;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaHora;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaNumerica;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaOpcionMultiple;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaTextual;
import com.trivialbox.controlpacientes.srv.objetos.Tools;
import java.util.ArrayList;
import java.util.Calendar;
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
        int idEncuesta = getIdEncuestaFromNombre(nombreEncuesta);
        puedeContestar(idEncuesta, idPaciente);
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        fields.add(new ObjectField("idPersona", idPaciente));
        fields.add(new ObjectField("fechaRespuesta", Tools.calendarToDateString(Calendar.getInstance())));
        
        ArrayList<String> table = new ArrayList<>();
        table.add("EncuestaPersona");
                
        dataBase.insert("EncuestaPersona", fields);
        
        String idEncuestaPersona = dataBase.select(table, fields).getRow(0).getField(0);
        
        for (Pregunta pregunta : preguntas)
            insertarRespuesta(idEncuesta, idEncuestaPersona, pregunta);
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

    private void puedeContestar(int idEncuesta, String idPaciente) {
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

    private void insertarRespuesta(int idEncuesta, String idEncuestaPersona, Pregunta pregunta) {
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idPregunta", Integer.toString(pregunta.getIdPregunta())));
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        fields.add(new ObjectField("idEncuestaPersona", idEncuestaPersona));
        dataBase.insert("Respuesta", fields);
        
        ArrayList<String> table = new ArrayList<>();
        table.add("Respuesta");
        
        String idRespuesta = dataBase.select(table, fields).getRow(0).getField(0);
        
        if (pregunta instanceof PreguntaTextual)
            insertarRespuestaPreguntaTextual(idRespuesta, (PreguntaTextual) pregunta);
        else if (pregunta instanceof PreguntaBooleana)
            insertarRespuestaPreguntaBooleana(idRespuesta, (PreguntaBooleana) pregunta);
        else if (pregunta instanceof PreguntaFecha)
            insertarRespuestaPreguntaFecha(idRespuesta, (PreguntaFecha) pregunta);
        else if (pregunta instanceof PreguntaHora)
            insertarRespuestaPreguntaHora(idRespuesta, (PreguntaHora) pregunta);
        else if (pregunta instanceof PreguntaNumerica)
            insertarRespuestaPreguntaNumerica(idRespuesta, (PreguntaNumerica) pregunta);
        else if (pregunta instanceof PreguntaOpcionMultiple)
            insertarRespuestaPreguntaOpcionMultiple(idRespuesta, (PreguntaOpcionMultiple) pregunta);
    }

    private void insertarRespuestaPreguntaTextual(String idRespuesta, PreguntaTextual pregunta) {
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("respuesta", pregunta.getRespuesta()));
        fields.add(new ObjectField("idRespuesta", idRespuesta));
        dataBase.insert("Respuesta" + Tools.getObjectName(pregunta), fields);
    }

    private void insertarRespuestaPreguntaBooleana(String idRespuesta, PreguntaBooleana pregunta) {
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("respuesta", pregunta.getRespuesta() ? "1" : "0"));
        fields.add(new ObjectField("idRespuesta", idRespuesta));
        dataBase.insert("Respuesta" + Tools.getObjectName(pregunta), fields);
    }

    private void insertarRespuestaPreguntaFecha(String idRespuesta, PreguntaFecha pregunta) {
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("respuesta", pregunta.getRespuesta()));
        fields.add(new ObjectField("idRespuesta", idRespuesta));
        dataBase.insert("Respuesta" + Tools.getObjectName(pregunta), fields);
    }

    private void insertarRespuestaPreguntaHora(String idRespuesta, PreguntaHora pregunta) {
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("respuesta", pregunta.getRespuesta()));
        fields.add(new ObjectField("idRespuesta", idRespuesta));
        dataBase.insert("Respuesta" + Tools.getObjectName(pregunta), fields);
    }

    private void insertarRespuestaPreguntaNumerica(String idRespuesta, PreguntaNumerica pregunta) {
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("respuesta", Double.toString(pregunta.getRespuesta())));
        fields.add(new ObjectField("idRespuesta", idRespuesta));
        dataBase.insert("Respuesta" + Tools.getObjectName(pregunta), fields);
    }

    private void insertarRespuestaPreguntaOpcionMultiple(String idRespuesta, PreguntaOpcionMultiple pregunta) {
        // TODO
        ArrayList<ObjectField> fields = new ArrayList<>();
        // fields.add(new ObjectField("respuesta", pregunta.getRespuesta()));
        fields.add(new ObjectField("idRespuesta", idRespuesta));
        dataBase.insert("Respuesta" + Tools.getObjectName(pregunta), fields);
    }
}
