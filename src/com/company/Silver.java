package com.company;

public class Silver extends Avion {

    public Silver(int capacidadCombustible, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, Ciudad actual) {
        super(capacidadCombustible, capacidadMaxPasajeros, velocidadMaxima, tipoPropulsion, true, actual);
    }
}
