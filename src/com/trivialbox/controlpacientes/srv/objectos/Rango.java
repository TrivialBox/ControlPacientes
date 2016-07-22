package com.trivialbox.controlpacientes.srv.objectos;

public class Rango {
    private final double limiteInferior;
    private final double limiteSuperior;
    public final boolean esIntervaloDiscreto;

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
