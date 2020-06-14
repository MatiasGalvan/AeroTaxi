package com.company;

public class Bronze extends Avion {
    public Bronze(int capacidadCombustible, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, Ciudad actual) {
        super(capacidadCombustible, capacidadMaxPasajeros, velocidadMaxima, tipoPropulsion, false, actual, 3000,"Bronze");
    }
}
