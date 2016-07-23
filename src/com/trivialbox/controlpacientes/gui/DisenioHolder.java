package com.trivialbox.controlpacientes.gui;

import javax.swing.JTextField;


public class DisenioHolder {
    String plomo = "#818181";
    String negro = "#000000";

    public void mensaje(JTextField letra, String Mensaje, int tam) {
        if (tam == 0) {
            letra.setText(Mensaje);
            letra.setForeground(java.awt.Color.decode(plomo));
        }
    }

    public void click(JTextField letra, String Mensaje) {
        if (letra.getText().equals(Mensaje)) {
            letra.setText("");
            letra.setForeground(java.awt.Color.decode(negro));
        }

    }
    
}
