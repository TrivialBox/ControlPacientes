package com.trivialbox.controlpacientes.srv;

import com.trivialbox.controlpacientes.srv.exceptions.FueraDeRangoException;
import com.trivialbox.controlpacientes.srv.exceptions.IntervaloNoValidoException;

/**
 * Como respuesta se tiene un número dentro de un rango indicado (inclusivo),
 * además se puede especificar si el intervalo es discreto o continuo (números enteros o reales).
 */
public class PreguntaNumerica extends Pregunta<Double, Double> {
    
    private Double respuesta = null;
    private final Rango rango;

    public PreguntaNumerica(int id, String titulo, boolean esOpcional, Rango rango) {
        super(id, titulo, esOpcional);
        this.rango = rango;
    }

    public Rango getRango() {
        return rango;
    }

    @Override
    public void responder(Double respuesta) {
        if (!rangoValido(respuesta))
            throw new FueraDeRangoException(respuesta, rango);
        if (!intervaloValido(respuesta))
            throw new IntervaloNoValidoException(respuesta, rango);
        this.respuesta = respuesta;
    }

    @Override
    protected boolean preguntaRespondida() {
        return respuesta != null;
    }

    private boolean rangoValido(Double respuesta) {
        return respuesta >= rango.getLimiteInferior() && respuesta <= rango.getLimiteSuperior();
    }

    private boolean intervaloValido(Double respuesta) {
        return !rango.esIntervaloDiscreto || esNumeroEntero(respuesta);
    }

    private boolean esNumeroEntero(Double respuesta) {
        return (respuesta - ((int) respuesta.doubleValue())) == 0;
    }

    @Override
    protected Double respuesta() {
        return respuesta;
    }

    @Override
    protected Double getRespuestaPorDefecto() {
        return null;  // No se ocurre nada mejor por ahora.
    }

}
