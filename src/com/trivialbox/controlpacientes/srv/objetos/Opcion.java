package com.trivialbox.controlpacientes.srv.objetos;

public class Opcion {
    private final String opcion;
    private boolean select;

    public Opcion(String opcion) {
        this.opcion = opcion;
        this.select = false;
    }

    public String getOpcion() {
        return opcion;
    }
    
    public void select() {
        this.select = true;
    }
    
    public void desSelect() {
        this.select = false;
    }
    
    public boolean isSelected() {
        return select;
    }
}
