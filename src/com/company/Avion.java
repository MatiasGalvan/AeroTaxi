package com.company;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Avion {
    private int capacidadCombustible;
    private int costoKm;
    private int capacidadMaxPasajeros;
    private double velocidadMaxima;
    private Propulsion tipoPropulsion;
    private boolean servicioCatering;
    private boolean disponible;
    private Ciudad actual;
    private HashMap<LocalDate, Vuelo> reservas;

    public Avion(int capacidadCombustible, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, boolean servicioCatering, Ciudad actual) {
        this.capacidadCombustible = capacidadCombustible;
        this.servicioCatering = servicioCatering;
        this.costoKm = costoKm();
        this.capacidadMaxPasajeros = capacidadMaxPasajeros;
        this.velocidadMaxima = velocidadMaxima;
        this.tipoPropulsion = tipoPropulsion;
        this.disponible = true;
        this.actual = actual;
        this.reservas = new HashMap<>();
    }

    public int getCapacidadMaxPasajeros() {
        return capacidadMaxPasajeros;
    }

    public void setCapacidadMaxPasajeros(int capacidadMaxPasajeros) {
        this.capacidadMaxPasajeros = capacidadMaxPasajeros;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Ciudad getActual() {
        return actual;
    }

    public void setActual(Ciudad actual) {
        this.actual = actual;
    }

    public int costoKm(){
        int res = 0;
        if(servicioCatering)
            res = (int) Math.random()*300+250;
        else
            res = (int) Math.random()*300+150;
        return res;
    }

    public void agregarReserva(Vuelo vuelo){
        reservas.put(vuelo.getFecha(), vuelo);
    }

    public void listarReservas(){
        int i = 0;
        for (Map.Entry<LocalDate, Vuelo> entry : reservas.entrySet()) {
            Vuelo v = entry.getValue();
            System.out.println(i + ". " + v.getFecha() + " " + v.getOrigen() + " " + v.getDestino());
            i++;
        }
    }

    public boolean disponibilidad (Vuelo vuelo){
        boolean res = false;

        if(!reservas.containsKey(vuelo.getFecha())) {
            LocalDate fecha = vuelo.getFecha();
            boolean vueloAnt = reservas.containsKey(fecha.minusDays(1));
            boolean vueloSig = reservas.containsKey(fecha.plusDays(1));

            if (vueloAnt && vueloSig) {
                Vuelo v1 = reservas.get(vuelo.getFecha().minusDays(1));
                Vuelo v2 = reservas.get(vuelo.getFecha().plusDays(1));
                if(v1.getDestino() == vuelo.getOrigen() && vuelo.getDestino() == v2.getOrigen())
                    res = true;
            }
            else if (vueloAnt && !vueloSig) {
                Vuelo v = reservas.get(vuelo.getFecha().minusDays(1));
                if (v.getDestino() == vuelo.getOrigen()) {
                    res = true;
                }
            }
            else if (vueloSig && !vueloAnt) {
                Vuelo v = reservas.get(vuelo.getFecha().plusDays(1));
                if (v.getOrigen() == vuelo.getDestino()) {
                    res = true;
                }
            }
            else {
                res = true;
            }
        }

        return res;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "costoKm=" + costoKm +
                ", capacidadMaxPasajeros=" + capacidadMaxPasajeros +
                ", servicioCatering=" + servicioCatering +
                ", actual=" + actual +
                '}';
    }
}
