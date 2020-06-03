package com.company;

public enum Propulsion {
    REACCION ("motor a reacción"),
    HELICE ("motor a hélice"),
    PISTON ("motor a pistones");

    private final String propulsion;

    Propulsion(String propulsion) {
        this.propulsion = propulsion;
    }
}
