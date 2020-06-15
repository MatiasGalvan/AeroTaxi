package com.company;

import com.company.exceptions.UsuarioNoExisteException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
            System.out.println( i + ". " + usuario.getNombre() + " " + usuario.getApellido() +
                    "\n DNI: " + usuario.getDni() + " Gasto total: " + usuario.getTotalGastado() +
                    " Mejor avion usado: " + usuario.getMejorAvionUsado()
            );
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
    public void agregarVuelo (Vuelo vuelo, Usuario usuario) {
        vuelo.getAvion().agregarReserva(vuelo);
        vuelo.agregarPasajeros(usuario, vuelo.getCantPasajeros());
        usuario.agregarVueloContratado(vuelo);
        listaVuelos.add(vuelo);
    }

    public void agregarVueloExistente (Vuelo vuelo, Usuario usuario, int cantPasajeros){
        usuario.agregarVueloContratado(vuelo);
        vuelo.agregarPasajeros(usuario, cantPasajeros);
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
            System.out.println( i + ". " + vuelo.getFecha() + " Origen: " + vuelo.getOrigen() + " Destino: " + vuelo.getDestino() + " Estado: " + vuelo.getEstado() + " Clase: " + vuelo.getClase());
            i++;
        }
    }

    public void listarVuelosPorFecha (LocalDate fecha){
        int i = 0;
        for (Vuelo vuelo : listaVuelos) {
            if(vuelo.getFecha().isEqual(fecha)) {
                System.out.println(i + ". " + vuelo.getFecha() + " Origen: " + vuelo.getOrigen() + " Destino: " + vuelo.getDestino() + " Estado: " + vuelo.getEstado() + " Clase: " + vuelo.getClase());
            }
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

    public Avion buscarAvionPorID(UUID idAvion){
        int i = 0;
        Avion res = null;
        while (i < listaAviones.size() && res == null){
            UUID id = listaAviones.get(i).getId();
            if(id.equals(idAvion)){
                res = listaAviones.get(i);
            }
            i++;
        }

        return res;
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

    public boolean cancelarVuelo(Vuelo vuelo, Usuario usuario){
        boolean res = false;
        if(vuelo.cancelarVuelo(usuario)){
            res = true;
            usuario.eliminarVueloContratado(vuelo.getId());
        }
        return res;
    }

    public Usuario buscarUsuarioPorID(UUID idUsuario){
        int i = 0;
        Usuario res = null;
        while (i < listaUsuarios.size() && res == null){
            UUID id = listaUsuarios.get(i).getId();
            if(id.equals(idUsuario)){
                res = listaUsuarios.get(i);
            }
            i++;
        }

        return res;
    }

    public void actualizar(){
        for (Vuelo vuelo : listaVuelos) {
            if( Main.fechaActual().isAfter(vuelo.getFecha()) && vuelo.getEstado() == 0){
                vuelo.setEstado(1);
                vuelo.getAvion().eliminarReserva(vuelo);
                vuelo.getAvion().setActual(vuelo.getDestino());
                HashMap<UUID, Integer> pasajeros = vuelo.getPasajeros();
                for (Map.Entry<UUID, Integer> entry : pasajeros.entrySet()) {
                    Usuario u = buscarUsuarioPorID(entry.getKey());
                    u.gastado(vuelo.getCostoTotal());
                    u.eliminarVueloContratado(vuelo.getId());
                }
            }
        }
    }
}
