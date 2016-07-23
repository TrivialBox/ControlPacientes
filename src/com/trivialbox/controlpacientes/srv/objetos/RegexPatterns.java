package com.trivialbox.controlpacientes.srv.objetos;

public class RegexPatterns {
    
    public static String email() {
        return "^([-0-9a-zA-Z_.]+)@([a-zA-Z]+)\\.([a-zA-Z]+)$";
    }
    
    public static String numeroCelular() {
        return "^(09)(\\d{8})$";
    }
    
    public static String soloNumeros() {
        return "^\\d+$";
    }
    
    public static String URL() {
        return "(https?//:)?(www)\\.([-a-zA-Z_0-9]+)\\.([a-zA-Z]+)";
    }
}
