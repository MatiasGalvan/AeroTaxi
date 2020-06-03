package com.company;

import java.time.LocalDate;

public class Vuelo {
    private Ciudad origen;
    private Ciudad destino;
    private int cantPasajeros;
    private Avion avion;
    private double costoTotal;
    private LocalDate fecha;

    public Vuelo(Ciudad origen, Ciudad destino, int cantPasajeros, LocalDate fecha) {
        this.origen = origen;
        this.destino = destino;
        this.cantPasajeros = cantPasajeros;
        this.fecha = fecha;
        this.costoTotal = 0;
        this.avion = null;
    }


}
