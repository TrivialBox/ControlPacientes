package com.trivialbox.controlpacientes.srv.objetos;

import com.trivialbox.controlpacientes.srv.exceptions.OpcionYaAgregadaException;
import com.trivialbox.controlpacientes.srv.exceptions.MaximoNumeroItemsSeleccionadosException;
import com.trivialbox.controlpacientes.srv.exceptions.SeleccionNoValidaException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Opciones {
    private final Map<String, Opcion> opciones;
    private final int numMaxSelecciones;
    private int numeroSeleccionados;
    public final boolean tieneCampoAdicional;
    
    /**
     * 
     * @param numMaxSelecciones 0 si no tiene límite.
     * @param tieneCampoAdicional 
     */
    public Opciones(int numMaxSelecciones, boolean tieneCampoAdicional) {
        this.opciones = new HashMap<>();
        this.numMaxSelecciones = numMaxSelecciones;
        this.tieneCampoAdicional = tieneCampoAdicional;
        this.numeroSeleccionados = numMaxSelecciones;
    }

    public int getNumMaxSelecciones() {
        return numMaxSelecciones;
    }

    public int getNumSelecciones() {
        return numMaxSelecciones - numeroSeleccionados;
    }
    
    public List<String> getOpciones() {
        return new ArrayList<>(opciones.keySet());
    }
    
    public void addOpcion(String opcion) {
        if (opciones.get(opcion) != null)
            throw new OpcionYaAgregadaException(opcion);
        else
            opciones.put(opcion, new Opcion(opcion));
    }
        
    public List<Opcion> getSeleccionados() {
        List<Opcion> seleccionados = new ArrayList<>();
        for (Opcion o : opciones.values())
            if (o.isSelected())
                seleccionados.add(o);
        return seleccionados;
    }
    
    public void select(String opcion) {
        if (numeroSeleccionados == 0 && numMaxSelecciones != 0)
            throw new MaximoNumeroItemsSeleccionadosException();
        
        Opcion o = opciones.get(opcion);
        if (o == null && !tieneCampoAdicional)
            throw new SeleccionNoValidaException(opcion);
        
        if (o == null) {
            Opcion aux = new Opcion(opcion);
            aux.select();
            opciones.put(opcion, aux);
        } else {
            o.select();
        }
        numeroSeleccionados--;
    }
}
