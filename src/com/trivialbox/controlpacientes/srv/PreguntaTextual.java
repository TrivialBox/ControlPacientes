package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.srv.exceptions.PatronException;
import com.trivialbox.controlpacientes.srv.exceptions.NumeroMaximoDeCaracteresException;
import com.trivialbox.controlpacientes.srv.exceptions.PatronYaDefinidoException;
import java.util.regex.Pattern;


/**
 * Pregunta tipo texto, opcionalmente se pueden definir
 * validaciones como una expresion regular, o un máximo de caracteres.
 * 
 * @author stsewd
 */
public class PreguntaTextual extends Pregunta<String, String> {

    private String respuesta;
    private Integer numMaxCaracteres = 0;
    private String patron = null;
    
    public PreguntaTextual(int id, String titulo, boolean esOpcional) {
        super(id, titulo, esOpcional);
    }

    public void setPatron(String patron) {
        if (this.patron != null)
            throw new PatronYaDefinidoException();
        this.patron = patron;
    }
    
    public PreguntaTextual(int id, String titulo, boolean esOpcional, Integer numMaxCaracteres) {
        this(id, titulo, esOpcional);
        this.numMaxCaracteres = numMaxCaracteres;
    }

    @Override
    public void responder(String respuesta) {
        if (!numeroDeCaracteresValidos(respuesta))
            throw new NumeroMaximoDeCaracteresException(respuesta.length(), numMaxCaracteres);
        if (!coincideConPatron(respuesta))
            throw new PatronException(respuesta);
        this.respuesta = respuesta;
    }
    
    private boolean numeroDeCaracteresValidos(String respuesta) {
        return numMaxCaracteres <= 0 || respuesta.length() <= numMaxCaracteres;
    }
    
    private boolean coincideConPatron(String respuesta) {
        if (patron == null)
            return true;
        return Pattern.matches(patron, respuesta);
    }

    @Override
    protected boolean preguntaRespondida() {
        return respuesta != null;
    }

    @Override
    protected String respuesta() {
        return respuesta;
    }

    @Override
    protected String getRespuestaPorDefecto() {
        return "";
    }
}
