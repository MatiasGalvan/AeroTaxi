package com.company;

public abstract class Avion {
    private int capacidadCombustible;
    private double costoKm;
    private int capacidadMaxPasajeros;
    private double velocidadMaxima;
    private Propulsion tipoPropulsion;
    private boolean servicioCatering;

    public Avion(int capacidadCombustible, double costoKm, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, boolean servicioCatering) {
        this.capacidadCombustible = capacidadCombustible;
        this.costoKm = costoKm;
        this.capacidadMaxPasajeros = capacidadMaxPasajeros;
        this.velocidadMaxima = velocidadMaxima;
        this.tipoPropulsion = tipoPropulsion;
        this.servicioCatering = servicioCatering;
    }
}
