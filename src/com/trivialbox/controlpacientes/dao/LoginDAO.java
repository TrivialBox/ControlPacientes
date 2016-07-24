package com.trivialbox.controlpacientes.dao;

import com.trivialbox.controlpacientes.dao.exceptions.AuntenticacionException;
import com.trivialbox.controlpacientes.dao.db.DataBase;
import com.trivialbox.controlpacientes.dao.db.DataBaseURL;
import com.trivialbox.controlpacientes.dao.db.LoginDB;
import com.trivialbox.controlpacientes.srv.objetos.Paciente;
import com.trivialbox.controlpacientes.srv.objetos.Tools;

public class LoginDAO {
    
    private static final String password = "1234";  // Ya sé, ya sé, pero no se me ocurre una solucion ahora.
    private static final String user = "controlpacientes";
    
    public static void autenticarAdmin(String cedula) {
        // TODO
    }
    
    public static void autenticarPaciente(String cedula, String password) {
        Paciente p = PacientesDAO.getInstance().get(cedula);
        if (p.getClave().equalsIgnoreCase(Tools.encryptPassword(password)))
            autenticar();
        else
            throw new AuntenticacionException();
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
