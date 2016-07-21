package com.trivialbox.controlpacientes.srv;

/**
 * Clase base para preguntas
 * @param <O> Opcion (de una lista de posibles respuestas)
 * @param <R> Respuesta(s)
 */
public abstract class Pregunta<O, R> {
    private final int id;    
    private String titulo;
    public final Boolean esOpcional;

    public Pregunta(int id, String titulo, boolean esOpcional) {
        this.id = id;
        this.titulo = titulo;
        this.esOpcional = esOpcional;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public abstract void responder(O respuesta);
    public abstract R getRespuesta();
    protected abstract boolean preguntaRespondida();
    
    public boolean estaRespondida() {
        return esOpcional || preguntaRespondida();
    }
}
