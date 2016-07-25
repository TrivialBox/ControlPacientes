package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.RowDB;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Paciente;
import com.trivialbox.controlpacientes.srv.objetos.Persona;
import com.trivialbox.controlpacientes.srv.objetos.Tools;
import java.util.ArrayList;
import java.util.Arrays;
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
        Paciente paciente;
        String[] tables = new String[]{"Persona", "Paciente"};
        ArrayList<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idpersona", cedula));
        TablaDB result = dataBase.select(Arrays.asList(tables), fields);
        paciente = construirPaciente(result, 0);
        return paciente;
    }
    
    private Paciente construirPaciente(TablaDB result, int index) {
        Paciente paciente;
        RowDB r = result.getRow(index);

        // Espero funciones :/
        paciente = new Paciente(
                r.getField(0),
                r.getField(5),
                r.getField(1),
                r.getField(2),
                r.getField(3),
                r.getField(4),
                r.getField(6),
                r.getField(7),
                r.getField(8)
        );
        
        return paciente;
    }

    public void del(String cedula) {
        try {
            Paciente paciente = get(cedula);
            List<ObjectField> fieldsPaciente = Tools.getValues(paciente, null);
            fieldsPaciente.addAll(getExtraFields(paciente));
            dataBase.delete(
                    Tools.getObjectName(paciente),
                    fieldsPaciente
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
            
            List<ObjectField> fieldsPaciente = Tools.getValues(paciente, null);
            fieldsPaciente.addAll(getExtraFields(paciente));
            dataBase.insert(
                    Tools.getObjectName(paciente),
                    fieldsPaciente
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
            
            List<ObjectField> fieldsPaciente = Tools.getValues(paciente, null);
            fieldsPaciente.addAll(getExtraFields(paciente));
            
            List<ObjectField> fieldsOldPaciente = Tools.getValues(pacienteOld, null);
            fieldsOldPaciente.addAll(getExtraFields(pacienteOld));

            dataBase.update(
                    Tools.getObjectName(paciente),
                    fieldsOldPaciente,
                    fieldsPaciente
            );
            
            return paciente;
        } catch(Exception e) {
            throw new RuntimeException("Error al actualizar paciente." + e.getMessage());
        }
    }

    public List<Paciente> get() {
        List<Paciente> pacientes = new ArrayList<>();
        String[] tables = new String[]{"Persona", "Paciente"};
        TablaDB result = dataBase.selectAll(Arrays.asList(tables), "idPersona");
        int index = 0;
        for (RowDB r : result)
            pacientes.add(construirPaciente(result, index++));
        return pacientes;
    }

    private List<ObjectField> getExtraFields(Paciente paciente) {
        ArrayList<ObjectField> extraFields = new ArrayList<>();
        extraFields.add(new ObjectField("idpersona", paciente.getIdPersona()));
        return extraFields;
    }
}
