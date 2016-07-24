package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.srv.objetos.Administrador;
import com.trivialbox.controlpacientes.srv.objetos.Persona;
import com.trivialbox.controlpacientes.srv.objetos.Tools;
import java.util.List;

public class AdministradoresDAO {
    
    private final DataBase dataBase;
    private static AdministradoresDAO instance;

    private AdministradoresDAO() {
        dataBase = DataBase.getInstance();
    }
    
    public static AdministradoresDAO getInstance() {
        if (instance == null)
            instance = new AdministradoresDAO();
        return instance;
    }
            
    public Administrador get(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void del(String cedula) {
        try {
            Administrador administrador = get(cedula);
            dataBase.delete(
                    Tools.getObjectName(administrador),
                    Tools.getValues(administrador, null)
            );
            
            dataBase.delete(
                    Persona.class.getSimpleName(),
                    administrador.getFieldsPersona()
            );
            
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar administrador." + ex.getMessage());
        }
    }

    public Administrador add(Administrador administrador) {
        try {
            dataBase.insert(
                    Persona.class.getSimpleName(),
                    administrador.getFieldsPersona()
            );
            
            dataBase.insert(
                    Tools.getObjectName(administrador),
                    Tools.getValues(administrador, null)
            );
            
            return administrador;
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar administrador." + ex.getMessage());
        }
    }

    public Administrador update(String cedula, Administrador administrador) {
        try {
            Administrador administradorOld = get(cedula);
            dataBase.update(
                    Persona.class.getSimpleName(),
                    administradorOld.getFieldsPersona(),
                    administrador.getFieldsPersona()
            );

            dataBase.update(
                    Tools.getObjectName(administrador),
                    Tools.getValues(administradorOld, null),
                    Tools.getValues(administrador, null)
            );
            
            return administrador;
        } catch(Exception e) {
            throw new RuntimeException("Error al actualizar administrador." + e.getMessage());
        }
    }

    public List<Administrador> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
