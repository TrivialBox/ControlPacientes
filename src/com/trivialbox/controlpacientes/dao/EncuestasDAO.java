package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.srv.objetos.Encuesta;
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
        return null;
    }
    
    public void del(String nombre) {
        
    }
    
    public void add(Encuesta encuesta) {
        
    }
    
    public void update(String nombre, Encuesta encuesta) {
        
    }
    
    public List<Encuesta> get() {
        return null;
    }
}
