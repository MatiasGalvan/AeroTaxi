package com.company;

public class Gold extends Avion {
    private boolean conexionWifi;

    public Gold(){

    }

    public Gold(int capacidadMaxPasajeros, Propulsion tipoPropulsion, Ciudad actual) {
        super(capacidadMaxPasajeros, tipoPropulsion, true, actual, 6000,Clase.GOLD);

        this.conexionWifi = conexionWifi();
    }

    public boolean conexionWifi(){
        boolean res = false;
        if(Math.random()*10+1 > 5 )
            res = true;
        return res;
    }
}
