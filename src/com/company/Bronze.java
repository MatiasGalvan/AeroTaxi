package com.company;

public class Bronze extends Avion {
    public Bronze(int capacidadCombustible, double costoKm, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion) {
        super(capacidadCombustible, costoKm, capacidadMaxPasajeros, velocidadMaxima, tipoPropulsion, false);
    }
}
