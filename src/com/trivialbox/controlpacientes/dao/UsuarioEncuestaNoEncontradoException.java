package com.trivialbox.controlpacientes.dao;

public class UsuarioEncuestaNoEncontradoException extends RuntimeException {

    public UsuarioEncuestaNoEncontradoException() {
        super("El usuario o la encuesta no existe.");
    }
    
}
