package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.dao.LoginDAO;

public class LoginSrv {
    
    public void autenticarAdmin(String cedula, String clave) {
        if (cedula == null || cedula.isEmpty())
            throw new IllegalArgumentException("Campo cédula no debe ser nulo.");
        if (clave == null || clave.isEmpty())
            throw new IllegalArgumentException("Campo clave no debe ser nulo.");
        LoginDAO.autenticarAdmin(cedula, clave);
    }
    
    public static void autenticarPaciente(String cedula, String clave) {
        if (cedula == null || cedula.isEmpty())
            throw new IllegalArgumentException("Campo cédula no debe ser nulo.");
        if (clave == null || clave.isEmpty())
            throw new IllegalArgumentException("Campo clave no debe ser nulo.");
        LoginDAO.autenticarPaciente(cedula, clave);
    }
    
    public static void cerrarSesion() {
        LoginDAO.cerrarSesion();
    }
}
