package com.trivialbox.controlpacientes.srv.objetos;

public class Paciente extends Persona {
    private String contactoEmergencia;
    private String fechaNacimiento;
    private String nivelAcademico;

    public Paciente(String idPersona,
                    String clave,
                    String nombre,
                    String direccion,
                    String telefono,
                    String sexo,
                    String contactoEmergencia,
                    String fechaNacimiento,
                    String nivelAcademico
    ) {
        
        super(idPersona, clave, nombre, direccion, telefono, sexo);
        this.contactoEmergencia = contactoEmergencia;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelAcademico = nivelAcademico;
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

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }
    
}
