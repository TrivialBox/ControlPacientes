package com.trivialbox.controlpacientes.dao.db;

import java.util.ArrayList;

public class RowDB {
    private final ArrayList<String> fields; 

    public RowDB() {
        this.fields = new ArrayList<>();
    }
    
    public void addField(String field) {
        this.fields.add(field);
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public String getField(int index) {
        return fields.get(index);
    }
    
}
