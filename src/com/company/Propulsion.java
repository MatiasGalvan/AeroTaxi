package com.company;

public enum Propulsion {
    REACCION ("Motor a reacción"),
    HELICE ("Motor a hélice"),
    PISTON ("Motor a pistones");

    private final String propulsion;

    Propulsion(String propulsion) {
        this.propulsion = propulsion;
    }

}
