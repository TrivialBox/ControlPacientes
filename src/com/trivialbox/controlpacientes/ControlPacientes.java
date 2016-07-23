package com.trivialbox.controlpacientes;

import com.trivialbox.controlpacientes.gui.Login;
import javax.swing.UIManager;

public class ControlPacientes {

    public static void main(String[] args) {
        try {
            //Look and feel para sistemas linux con gtk
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
        } 
        catch(Exception e){
            try {
                //Look and feel para sistemas windows
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception ex) {
                //Carga interfaz por defecto de java swing
            }
        }
        Login login = new Login();
        login.setVisible(true);
    }
    
}
