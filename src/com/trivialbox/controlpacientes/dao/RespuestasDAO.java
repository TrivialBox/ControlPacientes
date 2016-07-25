package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.exceptions.EncuestaYaRespondidaException;
import com.trivialbox.controlpacientes.dao.exceptions.EncuestaNoPermitidaException;
import com.trivialbox.controlpacientes.dao.exceptions.UsuarioEncuestaNoEncontradoException;
import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.RowDB;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Opcion;
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
     * Todas las respuestas de una pregunta.
     * @param nombreEncuesta
     * @param idPregunta
     * @return 
     */
    public List<Pregunta> get(String nombreEncuesta, int idPregunta) {
        ArrayList<Pregunta> preguntasContestadas = new ArrayList<>();
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        
        ArrayList<String> table = new ArrayList<>();
        table.add("Respuesta");
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        fields.add(new ObjectField("idPregunta", Integer.toString(idPregunta)));
        
        TablaDB result = dataBase.select(table, fields);
        
        for (RowDB row : result) {
            Pregunta pregunta = PreguntasDAO.getInstance().get(nombreEncuesta, idPregunta);
            responderPregunta(pregunta, row);
            preguntasContestadas.add(pregunta);
        }
        
        return preguntasContestadas;
    }
    
    /**
     * Todas las respuestas de una persona sobre una encuesta.
     * @param nombreEncuesta
     * @param idPaciente
     * @return 
     */
    public List<Pregunta> get(String nombreEncuesta, String idPaciente) {
        ArrayList<Pregunta> preguntasContestadas = new ArrayList<>();
        int idEncuesta = EncuestasDAO.getInstance().getIdEncuestaFromNombre(nombreEncuesta);
        /*
        ArrayList<String> table = new ArrayList<>();
        table.add("Respuesta");
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        fields.add(new ObjectField("idPregunta", Integer.toString(idPregunta)));
        
        TablaDB result = dataBase.select(table, fields);
        
        for (RowDB row : result) {
            Pregunta pregunta = PreguntasDAO.getInstance().get(nombreEncuesta, idPregunta);
            responderPregunta(pregunta, row);
            preguntasContestadas.add(pregunta);
        }
        */
        
        return preguntasContestadas;
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
        
        ArrayList<String> table = new ArrayList<>();
        table.add("Opcion");
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        
        fields.add(new ObjectField("idEncuesta", Integer.toString(pregunta.getIdEncuesta())));
        fields.add(new ObjectField("idPregunta", Integer.toString(pregunta.getIdPregunta())));
        
        TablaDB resultOpciones = dataBase.select(table, fields);
        
        for (Opcion opcion : pregunta.getRespuesta()) {
            fields = new ArrayList<>();
            fields.add(new ObjectField("idRespuesta", idRespuesta));
            fields.add(new ObjectField("idEncuesta", Integer.toString(pregunta.getIdEncuesta())));
            fields.add(new ObjectField("idPregunta", Integer.toString(pregunta.getIdPregunta())));
            fields.add(new ObjectField("idPregunta", getIdOpcion(resultOpciones, opcion)));
            dataBase.insert("RespuestaPreguntaOpcional", fields);
        }
    }

    private String getIdOpcion(TablaDB resultOpciones, Opcion opcion) {
        for (RowDB row : resultOpciones)
            if (row.getField(3).equalsIgnoreCase(opcion.getOpcion()))
                return row.getField(0);
        // TODO Crear nueva opcion
        return null;
    }

    private void responderPregunta(Pregunta pregunta, RowDB row) {
        if (pregunta instanceof PreguntaBooleana)
            responderPreguntaBooleana((PreguntaBooleana) pregunta, row);
        else if (pregunta instanceof PreguntaFecha)
            responderPreguntaFecha((PreguntaFecha) pregunta, row);
        else if (pregunta instanceof PreguntaHora)
            responderPreguntaHora((PreguntaHora) pregunta, row);
        else if (pregunta instanceof PreguntaNumerica)
            responderPreguntaNumerica((PreguntaNumerica) pregunta, row);
        else if (pregunta instanceof PreguntaOpcionMultiple)
            responderPreguntaOpcionMultiple((PreguntaOpcionMultiple) pregunta, row);
        else if (pregunta instanceof PreguntaTextual)
            responderPreguntaTextual((PreguntaTextual) pregunta, row);
    }

    private void responderPreguntaBooleana(PreguntaBooleana pregunta, RowDB row) {
        ArrayList<String> table = new ArrayList<>();
        table.add("RespuestaPreguntaBooleana");
       
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idRespuesta", row.getField(0)));
        
        TablaDB r = dataBase.select(table, fields);
        pregunta.responder(r.getRow(0).getField(1).compareTo("1") == 0);
    }

    private void responderPreguntaFecha(PreguntaFecha pregunta, RowDB row) {
        ArrayList<String> table = new ArrayList<>();
        table.add("RespuestaPreguntaFecha");
       
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idRespuesta", row.getField(0)));
        
        TablaDB r = dataBase.select(table, fields);
        pregunta.responder(r.getRow(0).getField(1));
    }

    private void responderPreguntaHora(PreguntaHora pregunta, RowDB row) {
        ArrayList<String> table = new ArrayList<>();
        table.add("RespuestaPreguntaHora");
       
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idRespuesta", row.getField(0)));
        
        TablaDB r = dataBase.select(table, fields);
        pregunta.responder(r.getRow(0).getField(1));
    }

    private void responderPreguntaNumerica(PreguntaNumerica pregunta, RowDB row) {
        ArrayList<String> table = new ArrayList<>();
        table.add("RespuestaPreguntaNumerica");
       
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idRespuesta", row.getField(0)));
        
        TablaDB r = dataBase.select(table, fields);
        pregunta.responder(Double.parseDouble(r.getRow(0).getField(1)));
    }

    private void responderPreguntaOpcionMultiple(PreguntaOpcionMultiple pregunta, RowDB row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void responderPreguntaTextual(PreguntaTextual pregunta, RowDB row) {
        ArrayList<String> table = new ArrayList<>();
        table.add("RespuestaPreguntaTextual");
       
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idRespuesta", row.getField(0)));
        
        TablaDB r = dataBase.select(table, fields);
        pregunta.responder(r.getRow(0).getField(1));
    }
}
