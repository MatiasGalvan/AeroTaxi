package com.company;

public class Gold extends Avion {
    private boolean conexionWifi;

    public Gold(int capacidadCombustible, double costoKm, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, boolean conexionWifi) {
        super(capacidadCombustible, costoKm, capacidadMaxPasajeros, velocidadMaxima, tipoPropulsion, true);
        this.conexionWifi = conexionWifi;
    }

}
