package com.trivialbox.controlpacientes.srv.exceptions;

/**
 *
 * @author Lara
 */
public class CedulaNoValidaException extends Exception {

    public CedulaNoValidaException(String cedula) {
         super("La cédula " + cedula + " no es valida.");
    }
    
    
}
