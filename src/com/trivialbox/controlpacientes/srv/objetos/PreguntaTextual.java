package com.trivialbox.controlpacientes.srv.objetos;

import com.trivialbox.controlpacientes.srv.exceptions.PatronException;
import com.trivialbox.controlpacientes.srv.exceptions.NumeroMaximoDeCaracteresException;
import com.trivialbox.controlpacientes.srv.exceptions.PatronYaDefinidoException;
import java.util.regex.Pattern;


/**
 * Pregunta tipo texto, opcionalmente se pueden definir
 * validaciones como una expresion regular, o un máximo de caracteres.
 */
public class PreguntaTextual extends Pregunta<String, String> {

    private String respuesta;
    private final Integer numMaxCaracteres;
    private String patron = null;
    
    /**
     * 
     * @param id
     * @param idEncuesta
     * @param titulo
     * @param esOpcional
     * @param numMaxCaracteres 0 si no tiene límite
     */
    public PreguntaTextual(int id, int idEncuesta, String titulo, boolean esOpcional, int numMaxCaracteres) {
        super(id, idEncuesta, titulo, esOpcional);
        this.numMaxCaracteres = numMaxCaracteres;
    }

    public void setPatron(String patron) {
        if (this.patron != null)
            throw new PatronYaDefinidoException();
        this.patron = patron;
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
