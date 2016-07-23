package com.trivialbox.controlpacientes.srv.objetos;

public class Paciente extends Persona {
    private String contactoEmergencia;
    private String fechaNacimiento;
    private String instrucccion;

    public Paciente(String idPersona,
                    String clave,
                    String nombre,
                    String direccion,
                    String telefono,
                    String sexo,
                    String contactoEmergencia,
                    String fechaNacimiento,
                    String instrucccion
    ) {
        
        super(idPersona, clave, nombre, direccion, telefono, sexo);
        this.contactoEmergencia = contactoEmergencia;
        this.fechaNacimiento = fechaNacimiento;
        this.instrucccion = instrucccion;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getInstrucccion() {
        return instrucccion;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setInstrucccion(String instrucccion) {
        this.instrucccion = instrucccion;
    }
    
}
