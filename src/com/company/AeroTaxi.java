package com.company;

import com.company.exceptions.UsuarioNoExisteException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class AeroTaxi {
    private LinkedList<Usuario> listaUsuarios;
    private LinkedList<Vuelo> listaVuelos;
    private LinkedList<Avion> listaAviones;

    public AeroTaxi() {
        this.listaUsuarios = new LinkedList<>();
        this.listaVuelos = new LinkedList<>();
        this.listaAviones = new LinkedList<>();
    }

    // ---------- ABM USUARIO ----------

    public void agregarUsuario (Usuario usuario) {
        if(usuario instanceof Usuario)
            listaUsuarios.add(usuario);
    }

    public void eliminarUsuario (Usuario usuario) {
        if(listaUsuarios.contains(usuario)){
            listaUsuarios.remove(usuario);
        }
        else{
            System.out.println("El usuario ingresado no se encuentra registrado.");
        }
    }

    public void listarUsuarios (){
        int i = 0;
        for (Usuario usuario : listaUsuarios) {
            System.out.println( i + ". " + usuario.getNombre() + " " + usuario.getApellido());
            i++;
        }
    }

    public Usuario validarUsuario(int dni) throws UsuarioNoExisteException {
        Usuario usuario = null;
        int i =0;
        while(i < listaUsuarios.size() && usuario == null){
            if(listaUsuarios.get(i).getDni() == dni) usuario = this.listaUsuarios.get(i);
            i++;
        }
        if(usuario == null) throw new UsuarioNoExisteException();
        return usuario;
    }
    // ---------- ABM VUELO ----------
    public void seleccionarOrigen(Vuelo vuelo, int id){
        switch (id){
            case 1:
                vuelo.setOrigen(Ciudad.BSAS);
                break;
            case 2:
                vuelo.setOrigen(Ciudad.CORDOBA);
                break;
            case 3:
                vuelo.setOrigen(Ciudad.SANTIAGO);
                break;
            case 4:
                vuelo.setOrigen(Ciudad.MONTEVIDEO);
                break;
        }
    }
    public void seleccionarDestino(Vuelo vuelo, int id){
        switch (id){
            case 1:
                vuelo.setDestino(Ciudad.BSAS);
                break;
            case 2:
                vuelo.setDestino(Ciudad.CORDOBA);
                break;
            case 3:
                vuelo.setDestino(Ciudad.SANTIAGO);
                break;
            case 4:
                vuelo.setDestino(Ciudad.MONTEVIDEO);
                break;
        }
    }
    public void agregarVuelo (Vuelo vuelo) {
            listaVuelos.add(vuelo);
    }

    public void eliminarVuelo (Vuelo vuelo) { //Cambiarla para que haga baja logica
        if(listaVuelos.contains(vuelo)){
            listaVuelos.remove(vuelo);
        }
        else{
            System.out.println("El vuelo ingresado no se encuentra registrado.");
        }
    }

    public void listarVuelos (){
        int i = 0;
        for (Vuelo vuelo : listaVuelos) {
            System.out.println( i + ". " + vuelo.getFecha() + " Origen: " + vuelo.getOrigen() + " Destino: " + vuelo.getDestino());
            i++;
        }
    }

    public boolean validarVuelo(Vuelo vuelo){
        boolean res = true;
        if(vuelo.getOrigen() == vuelo.getDestino()){
            res = false;
            System.out.println("El origen y destino no pueden ser iguales.");
        }
        return res;
    }
    public LinkedList<Vuelo> vuelosSimilares(Vuelo vuelo){
        LinkedList<Vuelo> vuelos = new LinkedList<>();
        int i = 0;
        while (i < listaVuelos.size()){
            Vuelo aux = listaVuelos.get(i);
            if(aux.similar(vuelo)) {
                if(aux.getCantPasajeros()+vuelo.getCantPasajeros() < aux.getAvion().getCapacidadMaxPasajeros()){
                    vuelos.add(aux);
                }
            }
            i++;
        }
        return vuelos;
    }

    // ---------- ABM AVION ----------

    public void agregarAvion (Avion avion) {
        if(avion instanceof Avion)
            listaAviones.add(avion);
    }

    public void eliminarAvion (Avion avion) {
        if(listaAviones.contains(avion)){
            listaAviones.remove(avion);
        }
        else{
            System.out.println("El avion ingresado no se encuentra registrado.");
        }
    }

    public void listarAviones (){
        int i = 0;
        for (Avion avion : listaAviones) {
            System.out.println( i + ". " + avion);
            i++;
        }
    }

    public Vuelo buscarVueloPorID(UUID idVuelo){
        int i = 0;
        Vuelo res = null;
        while (i < listaVuelos.size() && res == null){
            UUID id = listaVuelos.get(i).getId();
            if(id.equals(idVuelo)){
                res = listaVuelos.get(i);
            }
            i++;
        }

        return res;
    }

    public LinkedList<Avion> buscarAvionesDisponibles (Vuelo vuelo){
        LinkedList<Avion> disponibles = new LinkedList<>();
        HashMap<LocalDate, UUID> reservas;
        for (Avion avion : listaAviones) {
            reservas = avion.getReservas();

            if(!reservas.containsKey(vuelo.getFecha()) && reservas != null) {
                LocalDate fecha = vuelo.getFecha();
                boolean vueloAnt = reservas.containsKey(fecha.minusDays(1));
                boolean vueloSig = reservas.containsKey(fecha.plusDays(1));

                if (vueloAnt && vueloSig) {
                    Vuelo v1 = buscarVueloPorID(reservas.get(fecha.minusDays(1)));
                    Vuelo v2 = buscarVueloPorID(reservas.get(fecha.plusDays(1)));
                    if(v1.getDestino() == vuelo.getOrigen() && vuelo.getDestino() == v2.getOrigen())
                        disponibles.add(avion);
                }
                else if (vueloAnt && !vueloSig) {
                    Vuelo v = buscarVueloPorID(reservas.get(fecha.minusDays(1)));
                    if (v.getDestino() == vuelo.getOrigen()) {
                        disponibles.add(avion);
                    }
                }
                else if (vueloSig && !vueloAnt) {
                    Vuelo v = buscarVueloPorID(reservas.get(fecha.plusDays(1)));
                    if (v.getOrigen() == vuelo.getDestino()) {
                        disponibles.add(avion);
                    }
                }
                else {
                    disponibles.add(avion);
                }
            }
        }
        return disponibles;
    }
}
