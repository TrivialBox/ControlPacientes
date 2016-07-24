package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.exceptions.AutenticacionException;
import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.DataBaseURL;
import com.trivialbox.controlpacientes.dao.db.LoginDB;
import com.trivialbox.controlpacientes.srv.objetos.Administrador;
import com.trivialbox.controlpacientes.srv.objetos.Paciente;
import com.trivialbox.controlpacientes.srv.objetos.Tools;

public class LoginDAO {
    
    private static final String password = "1234";  // Ya sé, ya sé, pero no se me ocurre una solucion ahora.
    private static final String user = "controlpacientes";
    
    public static void autenticarAdmin(String cedula, String clave) {
        autenticar();
        Administrador a = AdministradoresDAO.getInstance().get(cedula);
        if (!a.getClave().equalsIgnoreCase(Tools.encryptPassword(clave))) {
            cerrarSesion();
            throw new AutenticacionException();
        }
    }
    
    public static void autenticarPaciente(String cedula, String clave) {
        autenticar();
        Paciente p = PacientesDAO.getInstance().get(cedula);
        if (!p.getClave().equalsIgnoreCase(Tools.encryptPassword(clave))) {
            cerrarSesion();
            throw new AutenticacionException();
        }
    }
    
    private static void autenticar() {
        LoginDB login = new LoginDB(DataBaseURL.getOracleURL());
        login.setUser(user);
        login.setPassword(password);
        DataBase.getInstance().conectar(login);
    }
    
    public static void cerrarSesion() {
        DataBase.deleteInstance();
    }
    
}
