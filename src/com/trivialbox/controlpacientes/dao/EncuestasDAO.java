package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.RowDB;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Encuesta;
import com.trivialbox.controlpacientes.srv.objetos.Tools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncuestasDAO {
    
    private final DataBase dataBase;
    private static EncuestasDAO instance;

    private EncuestasDAO() {
        dataBase = DataBase.getInstance();
    }
    
    public static EncuestasDAO getInstance() {
        if (instance == null)
            instance = new EncuestasDAO();
        return instance;
    }
    
    public Encuesta get(String nombre) {
        Encuesta encuesta;
        String[] tables = new String[]{"Encuesta"};
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("nombre", nombre));
        TablaDB result = dataBase.select(Arrays.asList(tables), fields);
        encuesta = construirEncuesta(result, 0);
        return encuesta;
    }
    
    public void del(String nombre) {
        throw new RuntimeException("Borrar una encuesta aún no está disponible");
    }
    
    public Encuesta add(Encuesta encuesta) {
        try {
            List<ObjectField> fieldsEncuesta = Tools.getValues(encuesta, getExcepciones());
            dataBase.insert(
                    Encuesta.class.getSimpleName(),
                    fieldsEncuesta
            );
            
            return get(encuesta.getNombre());
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar encuesta." + ex.getMessage());
        }
    }
    
    public Encuesta update(String nombre, Encuesta encuesta) {
        try {
            Encuesta encuestaOld = get(nombre);
            List<ObjectField> fieldsEncuesta = Tools.getValues(encuesta, getExcepciones());
            List<ObjectField> fieldsOldEncuesta = Tools.getValues(encuestaOld, null);

            dataBase.update(
                    Encuesta.class.getSimpleName(),
                    fieldsOldEncuesta,
                    fieldsEncuesta
            );
            
            return get(nombre);
        } catch(Exception e) {
            throw new RuntimeException("Error al actualizar encuesta." + e.getMessage());
        }
    }
    
    public List<Encuesta> get() {
        List<Encuesta> encuestas = new ArrayList<>();
        TablaDB result = dataBase.getAll("Encuesta");
        int index = 0;
        for (RowDB r : result)
            encuestas.add(construirEncuesta(result, index++));
        return encuestas;
    }

    private Encuesta construirEncuesta(TablaDB result, int index) {
        Encuesta encuesta;
        RowDB r = result.getRow(index);

        encuesta = new Encuesta(
                Integer.parseInt(r.getField(0)),
                r.getField(1)
        );
        encuesta.setDescripcion(r.getField(2));
        return encuesta;
    }

    private ArrayList<String> getExcepciones() {
        String[] exceptions = new String[] {"idEncuesta"};
        return new ArrayList<>(Arrays.asList(exceptions));
    }
    
    public int getIdEncuestaFromNombre(String nombreEncuesta) {
        int idEncuesta;
        ArrayList<String> table = new ArrayList<>();
        table.add("Encuesta");
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("nombre", nombreEncuesta));
        
        TablaDB result = dataBase.select(table, fields);
        
        idEncuesta = Integer.parseInt(result.getRow(0).getField(0));
        return idEncuesta;
    }
}
