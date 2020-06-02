package com.company;

public enum Ciudad {
    BSAS ("Buenos Aires"),
    CORDOBA ("Cordoba"),
    SANTIAGO ("Santiago de Chile"),
    MONTEVIDEO ("Montevideo");

    private final String nombre;

    Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static int distanciaKM(Ciudad origen, Ciudad destino){
        int distancia = 0;

        if(origen == BSAS && destino == CORDOBA || origen == CORDOBA && destino == BSAS )
            distancia = 695;
        if(origen == BSAS && destino == SANTIAGO || origen == SANTIAGO && destino == BSAS )
            distancia = 1400;

        return distancia;
    }
}
