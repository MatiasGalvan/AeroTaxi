package com.company;

public enum Propulsion {
    REACCION ("Motor a reacción", 1000,100),
    HELICE ("Motor a hélice", 3000,250),
    PISTON ("Motor a pistones", 2000,500);

    private final String propulsion;
    private final double velocidad;
    private final int combustible;

    Propulsion(String propulsion, double velocidad, int combustible) {
        this.propulsion = propulsion;
        this.velocidad = velocidad;
        this.combustible = combustible;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public int getCombustible() {
        return combustible;
    }

    public String getPropulsion() {
        return propulsion;
    }
}
