package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.PacientesDAO;
import com.trivialbox.controlpacientes.srv.exceptions.CedulaNoValidaException;
import com.trivialbox.controlpacientes.srv.objetos.Paciente;
import com.trivialbox.controlpacientes.srv.objetos.Tools;
import java.util.List;

/**
 *
 * @author Lara
 */
public class PacienteSRV {
    /**
     * 
     * @param cedula
     * @param clave
     * @param nombre
     * @param direccion
     * @param telefono
     * @param sexo
     * @param contactoEmergencia
     * @param fechaNacimiento
     * @param nivelAcademico
     * @return
     * @throws CedulaNoValidaException
     * @throws IllegalArgumentException 
     */
    public Paciente add(String cedula,
                    String clave,
                    String nombre,
                    String direccion,
                    String telefono,
                    String sexo,
                    String contactoEmergencia,
                    String fechaNacimiento,
                    String nivelAcademico) throws CedulaNoValidaException, IllegalArgumentException{
        if(cedula == null || cedula.trim().length()==0 || nombre.trim().length() ==0 )
            throw new IllegalArgumentException("Los campos no puede estar vacios");
        if(direccion.trim().length()==0 || telefono.trim().length()==0 || sexo.trim().length()==0 ||
                clave.trim().length()== 0)
            throw new IllegalArgumentException("Los campos no puede estar vacios");
        if(contactoEmergencia.trim().length()==0 || fechaNacimiento == null || nivelAcademico.trim().length()==0)
            throw new IllegalArgumentException("Los campos no puede estar vacios");
            Tools.validarCedula(cedula);
            Paciente paciente = new Paciente(cedula, clave, nombre, direccion, telefono, sexo, contactoEmergencia, fechaNacimiento, nivelAcademico);
            PacientesDAO.getInstance().add(paciente);
            return paciente;
    }
    
    /**
     * 
     * @param cedula
     * @return
     * @throws CedulaNoValidaException 
     */
    public Paciente get(String cedula) throws CedulaNoValidaException{
        if(cedula == null || cedula.trim().length()==0)
            throw new IllegalArgumentException("La cedula no puede estar vacia");
        Tools.validarCedula(cedula);
        return PacientesDAO.getInstance().get(cedula);
    }
    /**
     * 
     * @param cedula
     * @param clave
     * @param nombre
     * @param direccion
     * @param telefono
     * @param sexo
     * @param contactoEmergencia
     * @param fechaNacimiento
     * @param nivelAcademico
     * @return
     * @throws CedulaNoValidaException 
     */
    public Paciente update(String cedula,
                    String clave,
                    String nombre,
                    String direccion,
                    String telefono,
                    String sexo,
                    String contactoEmergencia,
                    String fechaNacimiento,
                    String nivelAcademico) throws CedulaNoValidaException{
        if(cedula == null || cedula.trim().length()==0 || nombre.trim().length() ==0 )
            throw new IllegalArgumentException("Los campos no puede estar vacios");
        if(direccion.trim().length()==0 || telefono.trim().length()==0 || sexo.trim().length()==0 ||
                clave.trim().length()== 0)
            throw new IllegalArgumentException("Los campos no puede estar vacios");
        if(contactoEmergencia.trim().length()==0 || fechaNacimiento == null || nivelAcademico.trim().length()==0)
            throw new IllegalArgumentException("Los campos no puede estar vacios");
            Tools.validarCedula(cedula);
        Paciente paciente = new Paciente(cedula, clave, nombre, direccion, telefono, sexo, contactoEmergencia, fechaNacimiento, nivelAcademico);
        PacientesDAO.getInstance().update(cedula, paciente);
        return paciente;
    }
     /**
      * 
      * @param cedula
      * @throws CedulaNoValidaException 
      */
    public void del(String cedula) throws CedulaNoValidaException{
        if(cedula == null || cedula.trim().length()==0)
            throw new IllegalArgumentException("La cedula no puede estar vacia");
        Tools.validarCedula(cedula);
        PacientesDAO.getInstance().del(cedula);
    }
    
    public List<Paciente> get(){
        return PacientesDAO.getInstance().get();
    }
}
