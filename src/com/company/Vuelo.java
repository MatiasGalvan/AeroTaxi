package com.company;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class Vuelo{
    private UUID id;
    private Ciudad origen;
    private Ciudad destino;
    private int cantPasajeros;
    private Avion avion;
    private double costoTotal;
    private LocalDate fecha;
    public int estado; // -1 cancelado, 0 pendiente, 1 finalizado
    private HashMap<UUID, Integer> pasajeros; //ID Del usuario
    private Clase clase;

    public Vuelo(){
        this.id = UUID.randomUUID();
        this.costoTotal = 0;
        this.avion = null;
        this.estado = 0;
        this.pasajeros = new HashMap<>();
        this.clase = null;
    }

    public Vuelo(Ciudad origen, Ciudad destino, int cantPasajeros, LocalDate fecha) {
        this.id = UUID.randomUUID();
        this.origen = origen;
        this.destino = destino;
        this.cantPasajeros = cantPasajeros;
        this.fecha = fecha;
        this.costoTotal = 0;
        this.avion = null;
        this.estado = 0;
        this.pasajeros = new HashMap<>();
        this.clase = null;
    }

    public HashMap<UUID, Integer> getPasajeros() {
        return pasajeros;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public void setCostoTotal() {
        this.costoTotal = calcularCosto();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public UUID getId() {
        return id;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public int getEstado() {
        return estado;
    }

    public boolean similar(Vuelo vuelo) {
        boolean res = false;
        if(fecha.equals(vuelo.getFecha())){
           if(origen == vuelo.getOrigen() && destino == vuelo.getDestino() && clase.equals(vuelo.getClase())){
               res = true;
           }
        }
        return res;
   }

   public void agregarPasajeros(Usuario usuario, int cantidad){
        if(cantPasajeros + cantidad < avion.getCapacidadMaxPasajeros()){
            cantPasajeros += cantidad;
            pasajeros.put(usuario.getId(), cantidad);
        }
    }

    public boolean cancelarVuelo (Usuario usuario){ //Devuelve si el usuario fue dado de baja del vuelo, no si el vuelo fue cancelado
        boolean res = false;
        UUID idUsuario = usuario.getId();

        if(Main.fechaActual().isBefore(fecha) && pasajeros.containsKey(idUsuario)){
            if(cantPasajeros - pasajeros.get(idUsuario) > 0){
                cantPasajeros -= pasajeros.get(idUsuario);
                pasajeros.remove(idUsuario);
            }
            else {
                estado = -1;
                avion.eliminarReserva(this);
            }
            res = true;
        }

        return res;
    }

    public double calcularCosto(){
        double res = 0;
        res = ((Ciudad.distanciaKM(origen, destino) * avion.getCostoKm()) + (cantPasajeros * 3500) + (avion.getTarifa()));
        return  res;
    }

    public double costoPorCant(int cant){
        double res = 0;
        res = ((Ciudad.distanciaKM(origen, destino) * avion.getCostoKm()) + (3500 * cant) + (avion.getTarifa()));
        return res;
    }

    @Override
    public String toString() {
        return "\nOrigen: " + origen +
                "\nDestino: " + destino +
                "\nCantidad de pasajeros: " + cantPasajeros +
                "\nAvion: " + avion.getId() +
                "\nCosto Total: " + costoTotal +
                "\nFecha: " + fecha;
    }
}
