package com.company;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Avion {
    private UUID id;
    private int capacidadCombustible;
    private int costoKm;
    private int capacidadMaxPasajeros;
    private int tarifa;
    private double velocidadMaxima;
    private Propulsion tipoPropulsion;
    private boolean servicioCatering;
    private Ciudad actual;
    private Clase clase;
    private HashMap<LocalDate, UUID> reservas;

    public Avion() {
        this.id = UUID.randomUUID();
        this.capacidadCombustible = 0;
        this.servicioCatering = false;
        this.costoKm = 0;
        this.capacidadMaxPasajeros = 0;
        this.velocidadMaxima = 0;
        this.tipoPropulsion = null;
        this.actual = null;
        this.reservas = new HashMap<>();
        this.tarifa = 0;
        this.clase = null;
    }
    public Avion(int capacidadCombustible, int capacidadMaxPasajeros, double velocidadMaxima, Propulsion tipoPropulsion, boolean servicioCatering, Ciudad actual, int tarifa, Clase clase) {
        this.id = UUID.randomUUID();
        this.capacidadCombustible = capacidadCombustible;
        this.servicioCatering = servicioCatering;
        this.costoKm = costoKm();
        this.capacidadMaxPasajeros = capacidadMaxPasajeros;
        this.velocidadMaxima = velocidadMaxima;
        this.tipoPropulsion = tipoPropulsion;
        this.actual = actual;
        this.reservas = new HashMap<>();
        this.tarifa = tarifa;
        this.clase = clase;
    }

    public int getCapacidadMaxPasajeros() {
        return capacidadMaxPasajeros;
    }

    public void setCapacidadMaxPasajeros(int capacidadMaxPasajeros) {
        this.capacidadMaxPasajeros = capacidadMaxPasajeros;
    }

    public Ciudad getActual() {
        return actual;
    }

    public void setActual(Ciudad actual) {
        this.actual = actual;
    }

    public int getCostoKm() {
        return costoKm;
    }

    public HashMap<LocalDate, UUID> getReservas() {
        return reservas;
    }

    public int getTarifa() {
        return tarifa;
    }

    public UUID getId() {
        return id;
    }

    public boolean isServicioCatering() {
        return servicioCatering;
    }

    public Clase getClase() {
        return clase;
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
        reservas.put(vuelo.getFecha(), vuelo.getId());
    }

    public void eliminarReserva(Vuelo vuelo) {
        reservas.remove(vuelo.getFecha());
    }

    public void listarReservas(){
        int i = 0;
        for (Map.Entry<LocalDate, UUID> entry : reservas.entrySet()) {
            System.out.println(i + ". Fecha: " + entry.getValue() + " ID de vuelo: " + entry.getKey() );
            i++;
        }
    }

    @Override
    public String toString() {
        return "Avion" +
                "\nCosto por Km: " + costoKm +
                "\nCapacidad maxima de pasajeros: " + capacidadMaxPasajeros +
                "\nServicio de catering: " + servicioCatering +
                "\nActual: " + actual;
    }
}
