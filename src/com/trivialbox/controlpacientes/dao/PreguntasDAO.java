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
import com.trivialbox.controlpacientes.srv.objetos.Tools;
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
        
        for (String opcion : getOpciones(pregunta.getIdEncuesta(), pregunta.getIdPregunta()))
            pregunta.addOpcion(opcion);
        
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
    
    private List<String> getOpciones(int idEncuesta, int idPregunta) {
        ArrayList<String> tabla = new ArrayList<>();
        tabla.add("Opcion");
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        fields.add(new ObjectField("idPregunta", Integer.toString(idPregunta)));
        
        TablaDB result = dataBase.select(tabla, fields);
        
        ArrayList<String> opciones = new ArrayList<>();
        
        for (RowDB row : result)
            opciones.add(row.getField(3));
        return opciones;
    }
    
    public void del(String nombreEncuesta, int idPregunta) {
        try {
            Pregunta pregunta = get(nombreEncuesta, idPregunta);
            
            List<ObjectField> fields = getExtraFields(pregunta);
            
            dataBase.delete(
                    "Opcion",
                    fields
            );
            
            dataBase.delete(
                    Tools.getObjectName(pregunta),
                    fields
            );
            
            dataBase.delete(
                    "Pregunta",
                    fields
            );
            
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar pregunta." + ex.getMessage());
        }
    }

    public Pregunta add(Pregunta pregunta) {
        try {
            dataBase.insert(
                    Pregunta.class.getSimpleName(),
                    pregunta.getFieldsPregunta()
            );
            
            if (pregunta instanceof PreguntaOpcionMultiple)
                return addOpcionMultiple((PreguntaOpcionMultiple) pregunta);
            
            List<ObjectField> fieldsPregunta = Tools.getValues(pregunta, null);
            fieldsPregunta.addAll(getExtraFields(pregunta));
            dataBase.insert(
                    Tools.getObjectName(pregunta),
                    fieldsPregunta
            );
            
            return pregunta;
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar pregunta." + ex.getMessage());
        }
    }

    public Pregunta update(String nombreEncuesta, int idPregunta, Pregunta pregunta) {
        // TODO Mejor seguimos haciendo otras cosas D:
        return null;
    }

    public List<Pregunta> get(String nombreEncuesta) {
        int idEncuesta = getIdEncuestaFromNombre(nombreEncuesta);
        List<Pregunta> preguntas = new ArrayList<>();
        ArrayList<String> table = new ArrayList<>();
        table.add("Pregunta");
        
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idEncuesta", Integer.toString(idEncuesta)));
        
        TablaDB result = dataBase.select(table, fields);
        for (RowDB r : result)
            preguntas.add(get(nombreEncuesta, Integer.parseInt(r.getField(0))));
        return preguntas;
    }

    private List<ObjectField> getExtraFields(Pregunta pregunta) {
        ArrayList<ObjectField> extraFields = new ArrayList<>();
        extraFields.add(new ObjectField("idEncuesta", Integer.toString(pregunta.getIdEncuesta())));
        extraFields.add(new ObjectField("idPregunta", Integer.toString(pregunta.getIdPregunta())));
        return extraFields;
    }

    private Pregunta addOpcionMultiple(PreguntaOpcionMultiple pregunta) {      
        ArrayList<ObjectField> fieldsPregunta = new ArrayList<>(getExtraFields(pregunta));
        fieldsPregunta.add(new ObjectField("numMaxSelecciones", Integer.toString(pregunta.getNumMaxSelecciones())));
        fieldsPregunta.add(new ObjectField("tieneCampoAdicional", pregunta.tieneCampoAdicional() ? "1" : "0"));
        
        dataBase.insert(
                "preguntaOpcionMultiple",
                fieldsPregunta
        );
        
        for (String opcion : pregunta.getOpciones()) {
            ArrayList<ObjectField> fieldsOpcion = new ArrayList<>();
            fieldsOpcion.add(new ObjectField("idEncuesta", Integer.toString(pregunta.getIdEncuesta())));
            fieldsOpcion.add(new ObjectField("idPregunta", Integer.toString(pregunta.getIdPregunta())));
            fieldsOpcion.add(new ObjectField("nombre", opcion));

            dataBase.insert(
                    "Opcion",
                    fieldsOpcion
            );
        }
        return pregunta;
    }
}
