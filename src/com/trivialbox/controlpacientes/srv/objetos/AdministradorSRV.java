package com.trivialbox.controlpacientes.srv.objetos;

import com.trivialbox.controlpacientes.dao.AdministradoresDAO;
import com.trivialbox.controlpacientes.srv.exceptions.CedulaNoValidaException;
import java.util.List;

/**
 *
 * @author Lara
 */
public class AdministradorSRV {
    /**
     * 
     * @param cedula
     * @return
     * @throws CedulaNoValidaException 
     */
    public Administrador get(String cedula) throws CedulaNoValidaException{
        if(cedula == null || cedula.trim().length()==0)
            throw new IllegalArgumentException("La cedula no puede estar vacia");
        Tools.validarCedula(cedula);
        return AdministradoresDAO.getInstance().get(cedula); 
    }
    /**
     * 
     * @param cedula
     * @param nombre
     * @param direccion
     * @param telefono
     * @param sexo
     * @param clave
     * @return
     * @throws CedulaNoValidaException 
     */
    public Administrador add(String cedula, String nombre, String direccion, String telefono, String sexo, String clave) throws CedulaNoValidaException{
        if(cedula == null || cedula.trim().length()==0 || nombre.trim().length() ==0 || direccion.trim().length()==0 || telefono.trim().length()==0 || sexo.trim().length()==0 || clave.trim().length()== 0)
            throw new IllegalArgumentException("Los campos no puede estar vacios");
        Tools.validarCedula(cedula);
        String cifrado = Tools.encryptPassword(clave);
        Administrador administrador = new Administrador(cedula, cifrado, nombre, direccion, telefono, sexo);
        AdministradoresDAO.getInstance().add(administrador);
        return administrador;
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
        AdministradoresDAO.getInstance().del(cedula);
    }
    
    public Administrador update(String cedula, String nombre, String direccion, String telefono, String sexo, String clave) throws CedulaNoValidaException{
        if(cedula == null || cedula.trim().length()==0 || nombre.trim().length() ==0 || direccion.trim().length()==0 || telefono.trim().length()==0 || sexo.trim().length()==0 || clave.trim().length()== 0)
            throw new IllegalArgumentException("Los campos no puede estar vacios");
        Tools.validarCedula(cedula);
        String cifrado = Tools.encryptPassword(clave);
        Administrador administrador = new Administrador(cedula, cifrado, nombre, direccion, telefono, sexo);
        AdministradoresDAO.getInstance().update(cedula, administrador);
        return administrador;
    }
    
    public List<Administrador> get(){
        return AdministradoresDAO.getInstance().get();
    }
    
    
}
