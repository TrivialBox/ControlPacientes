package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.srv.objetos.Paciente;
import com.trivialbox.controlpacientes.srv.objetos.Persona;
import com.trivialbox.controlpacientes.srv.objetos.Tools;
import java.util.List;

public class PacientesDAO {
    
    private final DataBase dataBase;
    private static PacientesDAO instance;

    private PacientesDAO() {
        dataBase = DataBase.getInstance();
    }
    
    public static PacientesDAO getInstance() {
        if (instance == null)
            instance = new PacientesDAO();
        return instance;
    }
            
    public Paciente get(String cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void del(String cedula) {
        try {
            Paciente paciente = get(cedula);
            dataBase.delete(
                    Tools.getObjectName(paciente),
                    Tools.getValues(paciente, null)
            );
            
            dataBase.delete(
                    Persona.class.getSimpleName(),
                    paciente.getFieldsPersona()
            );
            
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar paciente." + ex.getMessage());
        }
    }

    public Paciente add(Paciente paciente) {
        try {
            dataBase.insert(
                    Persona.class.getSimpleName(),
                    paciente.getFieldsPersona()
            );
            
            dataBase.insert(
                    Tools.getObjectName(paciente),
                    Tools.getValues(paciente, null)
            );
            
            return paciente;
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar paciente." + ex.getMessage());
        }
    }

    public Paciente update(String cedula, Paciente paciente) {
        try {
            Paciente pacienteOld = get(cedula);
            dataBase.update(
                    Persona.class.getSimpleName(),
                    pacienteOld.getFieldsPersona(),
                    paciente.getFieldsPersona()
            );

            dataBase.update(
                    Tools.getObjectName(paciente),
                    Tools.getValues(pacienteOld, null),
                    Tools.getValues(paciente, null)
            );
            
            return paciente;
        } catch(Exception e) {
            throw new RuntimeException("Error al actualizar paciente." + e.getMessage());
        }
    }

    public List<Paciente> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
