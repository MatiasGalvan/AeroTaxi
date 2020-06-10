package com.company;

public enum Ciudad {
    BSAS ("Buenos Aires"),
    CORDOBA ("Cordoba"),
    SANTIAGO ("Santiago de Chile"),
    MONTEVIDEO ("Montevideo");

    private final String nombre;
    private static Object distancias[][] = cargarMatriz();

    Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Object [][] cargarMatriz(){
        Object distancias [] [] = new Object[6][3];
        distancias [0][0] = BSAS; distancias [0][1] = CORDOBA; distancias [0][2] = 695;
        distancias [1][0] = BSAS; distancias [1][1] = SANTIAGO; distancias [1][2] = 1400;
        distancias [2][0] = BSAS; distancias [2][1] = MONTEVIDEO; distancias [2][2] = 950;
        distancias [3][0] = CORDOBA; distancias [3][1] = MONTEVIDEO; distancias [3][2] = 1190;
        distancias [4][0] = CORDOBA; distancias [4][1] = SANTIAGO; distancias [4][2] = 1050;
        distancias [5][0] = MONTEVIDEO; distancias [5][1] = SANTIAGO; distancias [5][2] = 2100;
        return distancias;
    }

    public static int distanciaKM(Ciudad origen, Ciudad destino){
        int distancia = 0, i = 0;

        while (i < distancias.length && distancia == 0) {
          if(distancias [i] [0] == origen && distancias[i] [1] == destino || distancias [i] [0] == destino && distancias[i] [1] == origen){
                  distancia = (int) distancias [i] [2];
          }
          i++;
        }

        return distancia;
    }

}
