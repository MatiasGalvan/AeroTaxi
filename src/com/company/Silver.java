package com.company;

public class Silver extends Avion {

    public Silver(int capacidadCombustible, double costoKm, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, boolean servicioCatering) {
        super(capacidadCombustible, costoKm, capacidadMaxPasajeros, velocidadMaxima, tipoPropulsion, true);
    }
}
