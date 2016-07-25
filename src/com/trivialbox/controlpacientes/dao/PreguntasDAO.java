package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.exceptions.PreguntaNoExistenteException;
import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.RowDB;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Pregunta;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaTextual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreguntasDAO {
    private final DataBase dataBase;
    private static PreguntasDAO instance;

    private PreguntasDAO() {
        dataBase = DataBase.getInstance();
    }
    
    public static PreguntasDAO getInstance() {
        if (instance == null)
            instance = new PreguntasDAO();
        return instance;
    }
            
    public Pregunta get(String nombreEncuesta, int idPregunta) {
        Pregunta pregunta = null;
        int idEncuesta = getIdEncuestaFromNombre(nombreEncuesta);
        
        try {
            pregunta = getFromPreguntaTextual(idEncuesta, idPregunta);
            return pregunta;
        } catch (Exception e) { /* ok, sigamos buscando */}
        
        try {
            pregunta = getFromPreguntaBooleana(idEncuesta, idPregunta);
            return pregunta;
        } catch (Exception e) { /* ok, sigamos buscando */}
        
        try {
            pregunta = getFromPreguntaFecha(idEncuesta, idPregunta);
            return pregunta;
        } catch (Exception e) { /* ok, sigamos buscando */}
        
        try {
            pregunta = getFromPreguntaHora(idEncuesta, idPregunta);
            return pregunta;
        } catch (Exception e) { /* ok, sigamos buscando */}
        
        try {
            pregunta = getFromPreguntaNumerica(idEncuesta, idPregunta);
            return pregunta;
        } catch (Exception e) { /* ok, sigamos buscando */}
        
        try {
            pregunta = getFromPreguntaOpcionMultiple(idEncuesta, idPregunta);
            return pregunta;
        } catch (Exception e) { /* ok, sigamos buscando */}
        
        throw new PreguntaNoExistenteException(nombreEncuesta, idPregunta);
    }
    
    private Pregunta getFromPreguntaTextual(int idEncuesta, int idPregunta) {
        Pregunta pregunta;
        String[] tables = new String[]{"Pregunta", "PreguntaTextual"};
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        fields.add(new ObjectField("idPregunta", Integer.toString(idPregunta)));
        
        TablaDB result = dataBase.select(Arrays.asList(tables), fields);
        pregunta = construirPreguntaTextual(result, 0);
        return pregunta;
    }

    public void del(String nombreEncuesta, int idPregunta) {
    }

    public Pregunta add(Pregunta pregunta) {
        return null;
    }

    public Pregunta update(String nombreEncuesta, int idPregunta, Pregunta pregunta) {
        return null;
    }

    public List<Pregunta> get() {
        return null;
    }

    private Pregunta construirPreguntaTextual(TablaDB result, int index) {
        PreguntaTextual preguntaTextual;
        RowDB r = result.getRow(index);
        preguntaTextual = new PreguntaTextual(
                Integer.parseInt(r.getField(0)),
                Integer.parseInt(r.getField(1)),
                r.getField(2),
                r.getField(3).compareTo("1") == 0,
                Integer.parseInt(r.getField(5))
        );
        preguntaTextual.setPatron(r.getField(6));
        
        return preguntaTextual;
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

    private Pregunta getFromPreguntaBooleana(int idEncuesta, int idPregunta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Pregunta getFromPreguntaFecha(int idEncuesta, int idPregunta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Pregunta getFromPreguntaHora(int idEncuesta, int idPregunta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Pregunta getFromPreguntaNumerica(int idEncuesta, int idPregunta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Pregunta getFromPreguntaOpcionMultiple(int idEncuesta, int idPregunta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
