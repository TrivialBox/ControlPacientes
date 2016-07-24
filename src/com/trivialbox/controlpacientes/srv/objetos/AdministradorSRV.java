package com.trivialbox.controlpacientes.srv.objetos;

import com.trivialbox.controlpacientes.dao.AdministradoresDAO;

/**
 *
 * @author Lara
 */
public class AdministradorSRV {
    
    public Administrador get(String cedula){
        
        return AdministradoresDAO.getInstance().get(cedula); 
    }

    
}
