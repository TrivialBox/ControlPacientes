package com.trivialbox.controlpacientes.srv.objetos;

public class Rango {
    private final Double limiteInferior;
    private final Double limiteSuperior;
    public final Boolean esIntervaloDiscreto;

    public Rango(double limiteInferior, double limiteSuperior, boolean esIntervaloDiscreto) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.esIntervaloDiscreto = esIntervaloDiscreto;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }
    
}
