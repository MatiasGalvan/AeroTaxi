package com.company;

public class Bronze extends Avion {
    public Bronze(int capacidadMaxPasajeros, Propulsion tipoPropulsion, Ciudad actual) {
        super(capacidadMaxPasajeros, tipoPropulsion, false, actual, 3000,Clase.BRONZE);
    }
}
