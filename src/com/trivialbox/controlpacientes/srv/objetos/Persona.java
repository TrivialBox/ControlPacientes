package com.trivialbox.controlpacientes.srv.objetos;

import com.trivialbox.controlpacientes.dao.db.ObjectField;
import java.util.ArrayList;
import java.util.List;

public abstract class Persona {
    protected final String idPersona;
    private String clave;
    
    private String nombre;
    private String direccion;
    private String telefono;
    private String sexo;

    public Persona(String idPersona, String clave, String nombre, String direccion, String telefono, String sexo) {
        this.idPersona = idPersona;
        this.clave = clave;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public List<ObjectField> getFieldsPersona() {
        List<ObjectField> fields = new ArrayList<>();
        fields.add(new ObjectField("idpersona", getIdPersona()));
        fields.add(new ObjectField("nombre", getNombre()));
        fields.add(new ObjectField("direccion", getDireccion()));
        fields.add(new ObjectField("telefono", getTelefono()));
        fields.add(new ObjectField("sexo", getSexo()));
        fields.add(new ObjectField("clave", getClave()));
        return fields;
    }
}
