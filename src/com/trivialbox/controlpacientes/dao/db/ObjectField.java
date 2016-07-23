package com.trivialbox.controlpacientes.dao.db;

public class ObjectField {
    private final String name;
    private final String value;

    public ObjectField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
