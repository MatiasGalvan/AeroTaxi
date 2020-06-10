package com.company;

public class Gold extends Avion {
    private boolean conexionWifi;

    public Gold(int capacidadCombustible, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, Ciudad actual) {
        super(capacidadCombustible, capacidadMaxPasajeros, velocidadMaxima, tipoPropulsion, true, actual, 6000);
        this.conexionWifi = conexionWifi();
    }

    public boolean conexionWifi(){
        boolean res = false;
        if(Math.random()*10+1 > 5 )
            res = true;
        return res;
    }
}
