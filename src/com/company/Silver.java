package com.company;

public class Silver extends Avion {
    public Silver(){

    }
    public Silver(int capacidadMaxPasajeros, Propulsion tipoPropulsion, Ciudad actual) {
        super(capacidadMaxPasajeros, tipoPropulsion, true, actual, 4000,Clase.SILVER);
    }
}
