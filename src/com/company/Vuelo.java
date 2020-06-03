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

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "\nOrigen: " + origen +
                "\nDestino: " + destino +
                "\nCantidad de pasajeros: " + cantPasajeros +
                "\nAvion: " + avion +
                "\nCosto Total: " + costoTotal +
                "\nFecha: " + fecha;
    }
}
