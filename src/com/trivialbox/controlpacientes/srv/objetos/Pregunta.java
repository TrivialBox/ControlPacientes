package com.trivialbox.controlpacientes.srv.objetos;

import com.trivialbox.controlpacientes.srv.exceptions.PreguntaNoRespondidaException;

/**
 * Clase base para preguntas
 * @param <O> Opcion (de una lista de posibles respuestas)
 * @param <R> Respuesta(s)
 */
public abstract class Pregunta<O, R> {
    protected final Integer idPregunta;
    protected final Integer idEncuesta;
    
    private String titulo;
    private final Boolean esOpcional;
    private String descripcion = "";

    public Pregunta(int idPregunta, int idEncuesta, String titulo, boolean esOpcional) {
        this.idPregunta = idPregunta;
        this.idEncuesta = idEncuesta;
        this.titulo = titulo;
        this.esOpcional = esOpcional;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public boolean esOpcional() {
        return esOpcional;
    }
    
    public abstract void responder(O respuesta);
    protected abstract R respuesta();
    protected abstract R getRespuestaPorDefecto();
    protected abstract boolean preguntaRespondida();
    
    public final R getRespuesta() {
        if (esOpcional && !preguntaRespondida())
            return getRespuestaPorDefecto();
        else if (preguntaRespondida())
            return respuesta();
        else
            throw new PreguntaNoRespondidaException(this.idPregunta);
    }
    
    public final boolean estaRespondida() {
        return esOpcional || preguntaRespondida();
    }
}
