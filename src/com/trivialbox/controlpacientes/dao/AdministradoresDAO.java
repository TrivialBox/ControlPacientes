package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.ObjectField;
import com.trivialbox.controlpacientes.dao.db.RowDB;
import com.trivialbox.controlpacientes.dao.db.TablaDB;
import com.trivialbox.controlpacientes.srv.objetos.Administrador;
import com.trivialbox.controlpacientes.srv.objetos.Persona;
import com.trivialbox.controlpacientes.srv.objetos.Tools;
import java.util.ArrayList;
import java.util.Arrays;
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
        Administrador administrador;
        String[] tables = new String[]{"Persona", "Administrador"};
        TablaDB result = dataBase.select(Arrays.asList(tables), "idpersona", cedula);
        administrador = construirAdministrador(result, 0);
        return administrador;
    }

    public void del(String cedula) {
        try {
            Administrador administrador = get(cedula);
            List<ObjectField> fieldsAdministrador = Tools.getValues(administrador, null);
            fieldsAdministrador.addAll(getExtraFields(administrador));
            dataBase.delete(
                    Tools.getObjectName(administrador),
                    fieldsAdministrador
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
            
            List<ObjectField> fieldsAdministrador = Tools.getValues(administrador, null);
            fieldsAdministrador.addAll(getExtraFields(administrador));
            
            dataBase.insert(
                    Tools.getObjectName(administrador),
                    fieldsAdministrador
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
            
            List<ObjectField> fieldsPaciente = Tools.getValues(administrador, null);
            fieldsPaciente.addAll(getExtraFields(administrador));
            
            List<ObjectField> fieldsOldPaciente = Tools.getValues(administradorOld, null);
            fieldsOldPaciente.addAll(getExtraFields(administradorOld));

            dataBase.update(
                    Tools.getObjectName(administrador),
                    fieldsOldPaciente,
                    fieldsPaciente
            );
            
            return administrador;
        } catch(Exception e) {
            throw new RuntimeException("Error al actualizar administrador." + e.getMessage());
        }
    }

    public List<Administrador> get() {
        List<Administrador> administradores = new ArrayList<>();
        String[] tables = new String[]{"Persona", "Paciente"};
        TablaDB result = dataBase.selectAll(Arrays.asList(tables), "idPersona");
        int index = 0;
        for (RowDB r : result)
            administradores.add(construirAdministrador(result, index++));
        return administradores;
    }

    private Administrador construirAdministrador(TablaDB result, int index) {
        Administrador administrador;
        RowDB r = result.getRow(index);

        // Espero funciones :/
        administrador = new Administrador(
                r.getField(0),
                r.getField(5),
                r.getField(1),
                r.getField(2),
                r.getField(3),
                r.getField(4)
        );
        administrador.setFechaUltimoAcceso(r.getField(7));
        return administrador;
    }

    private List<ObjectField> getExtraFields(Administrador administrador) {
        ArrayList<ObjectField> extraFields = new ArrayList<>();
        extraFields.add(new ObjectField("idpersona", administrador.getIdPersona()));
        return extraFields;
    }
}
