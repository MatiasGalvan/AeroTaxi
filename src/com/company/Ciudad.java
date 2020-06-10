package com.company;

public enum Ciudad {
    BSAS ("Buenos Aires"),
    CORDOBA ("Cordoba"),
    SANTIAGO ("Santiago de Chile"),
    MONTEVIDEO ("Montevideo");

    private final String nombre;
    private static Object costos[][] = cargarMatriz();

    Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Object [][] cargarMatriz(){
        Object costos [] [] = new Object[6][3];
        costos [0][0] = BSAS; costos [0][1] = CORDOBA; costos [0][2] = 695;
        costos [1][0] = BSAS; costos [1][1] = SANTIAGO; costos [1][2] = 1400;
        costos [2][0] = BSAS; costos [2][1] = MONTEVIDEO; costos [2][2] = 950;
        costos [3][0] = CORDOBA; costos [3][1] = MONTEVIDEO; costos [3][2] = 1190;
        costos [4][0] = CORDOBA; costos [4][1] = SANTIAGO; costos [4][2] = 1050;
        costos [5][0] = MONTEVIDEO; costos [5][1] = SANTIAGO; costos [5][2] = 2100;
        return costos;
    }

    public static int distanciaKM(Ciudad origen, Ciudad destino){
        int distancia = 0, i = 0;

        while (i < costos.length && distancia == 0) {
          if(costos [i] [0] == origen && costos[i] [1] == destino || costos [i] [0] == destino && costos[i] [1] == origen){
                  distancia = (int) costos [i] [2];
          }
          i++;
        }

        return distancia;
    }

}
