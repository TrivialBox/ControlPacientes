package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.exceptions.PreguntaNoExistenteException;
import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.RowDB;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Pregunta;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaBooleana;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaFecha;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaHora;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaNumerica;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaOpcionMultiple;
import com.trivialbox.controlpacientes.srv.objetos.PreguntaTextual;
import com.trivialbox.controlpacientes.srv.objetos.Rango;
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
    
    private TablaDB getPreguntaFromTable(String table, int idEncuesta, int idPregunta) {
        String[] tables = new String[]{"Pregunta", table};
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        fields.add(new ObjectField("idPregunta", Integer.toString(idPregunta)));
        
        TablaDB result = dataBase.select(Arrays.asList(tables), fields);
        return result;
    }
    
    private Pregunta getFromPreguntaTextual(int idEncuesta, int idPregunta) {
        Pregunta pregunta;        
        TablaDB result = getPreguntaFromTable("PreguntaTextual", idEncuesta, idPregunta);
        pregunta = construirPreguntaTextual(result, 0);
        return pregunta;
    }
    
    private Pregunta getFromPreguntaBooleana(int idEncuesta, int idPregunta) {
        Pregunta pregunta;        
        TablaDB result = getPreguntaFromTable("PreguntaBooleana", idEncuesta, idPregunta);
        pregunta = construirPreguntaBooleana(result, 0);
        return pregunta;
    }

    private Pregunta getFromPreguntaFecha(int idEncuesta, int idPregunta) {
        Pregunta pregunta;        
        TablaDB result = getPreguntaFromTable("PreguntaFecha", idEncuesta, idPregunta);
        pregunta = construirPreguntaFecha(result, 0);
        return pregunta;
    }

    private Pregunta getFromPreguntaHora(int idEncuesta, int idPregunta) {
        Pregunta pregunta;        
        TablaDB result = getPreguntaFromTable("PreguntaHora", idEncuesta, idPregunta);
        pregunta = construirPreguntaHora(result, 0);
        return pregunta;
    }

    private Pregunta getFromPreguntaNumerica(int idEncuesta, int idPregunta) {
        Pregunta pregunta;        
        TablaDB result = getPreguntaFromTable("PreguntaNumerica", idEncuesta, idPregunta);
        pregunta = construirPreguntaNumerica(result, 0);
        return pregunta;
    }

    private Pregunta getFromPreguntaOpcionMultiple(int idEncuesta, int idPregunta) {
        Pregunta pregunta;        
        TablaDB result = getPreguntaFromTable("PreguntaOpcionMultiple", idEncuesta, idPregunta);
        pregunta = construirPreguntaOpcionMultiple(result, 0);
        return pregunta;
    }

    private Pregunta construirPreguntaTextual(TablaDB result, int index) {
        PreguntaTextual pregunta;
        RowDB r = result.getRow(index);
        pregunta = new PreguntaTextual(
                Integer.parseInt(r.getField(0)),
                Integer.parseInt(r.getField(1)),
                r.getField(2),
                r.getField(3).compareTo("1") == 0,
                Integer.parseInt(r.getField(5))
        );
        pregunta.setPatron(r.getField(6));
        
        return pregunta;
    }
    
    private Pregunta construirPreguntaBooleana(TablaDB result, int index) {
        PreguntaBooleana pregunta;
        RowDB r = result.getRow(index);
        pregunta = new PreguntaBooleana(
                Integer.parseInt(r.getField(0)),
                Integer.parseInt(r.getField(1)),
                r.getField(2),
                r.getField(3).compareTo("1") == 0
        );
        
        return pregunta;
    }
    
    private Pregunta construirPreguntaFecha(TablaDB result, int index) {
        PreguntaFecha pregunta;
        RowDB r = result.getRow(index);
        pregunta = new PreguntaFecha(
                Integer.parseInt(r.getField(0)),
                Integer.parseInt(r.getField(1)),
                r.getField(2),
                r.getField(3).compareTo("1") == 0
        );
        
        return pregunta;
    }
    
    private Pregunta construirPreguntaHora(TablaDB result, int index) {
        PreguntaHora pregunta;
        RowDB r = result.getRow(index);
        pregunta = new PreguntaHora(
                Integer.parseInt(r.getField(0)),
                Integer.parseInt(r.getField(1)),
                r.getField(2),
                r.getField(3).compareTo("1") == 0
        );
        
        return pregunta;
    }
    
    private Pregunta construirPreguntaNumerica(TablaDB result, int index) {
        PreguntaNumerica pregunta;
        RowDB r = result.getRow(index);
        Rango rango = new Rango(
                Double.parseDouble(r.getField(5)),
                Double.parseDouble(r.getField(6)),
                r.getField(7).compareTo("1") == 0
        );
        
        pregunta = new PreguntaNumerica(
                Integer.parseInt(r.getField(0)),
                Integer.parseInt(r.getField(1)),
                r.getField(2),
                r.getField(3).compareTo("1") == 0,
                rango
        );
        
        return pregunta;
    }
    
    private Pregunta construirPreguntaOpcionMultiple(TablaDB result, int index) {
        PreguntaOpcionMultiple pregunta;
        RowDB r = result.getRow(index);
        pregunta = new PreguntaOpcionMultiple(
                Integer.parseInt(r.getField(0)),
                Integer.parseInt(r.getField(1)),
                r.getField(2),
                r.getField(3).compareTo("1") == 0,
                Integer.parseInt(r.getField(5)),
                r.getField(6).compareTo("1") == 0
        );
        
        // TODO falta de construir
        
        return pregunta;
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
    
}
