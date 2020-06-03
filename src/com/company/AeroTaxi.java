package com.company;

import java.util.HashMap;
import java.util.Map;

public class AeroTaxi {
    private HashMap<Integer, Usuario> listaUsuarios;
    private HashMap<Integer, Vuelo> listaVuelos;
    private HashMap<Integer, Avion> listaAviones;

    public AeroTaxi() {
        this.listaUsuarios = new HashMap<Integer, Usuario>();
        this.listaVuelos = new HashMap<Integer, Vuelo>();
        this.listaAviones = new HashMap<Integer, Avion>();
    }

    // ---------- ABM USUARIO ----------

    public void agregarUsuario (Usuario usuario) {
        if(usuario instanceof Usuario)
            listaUsuarios.put(usuario.getDni(), usuario);
    }

    public void eliminarUsuario (Usuario usuario) {
        boolean existe = false;
        for (Map.Entry<Integer, Usuario> entry : listaUsuarios.entrySet()) {
            Usuario u = (Usuario) entry.getValue();
            if(u.equals(usuario)){
                existe = true;
            }
        }

        if(existe){
            listaUsuarios.remove(usuario.getDni());
        }
        else{
            System.out.println("El usuario ingresado no se encuentra registrado.");
        }
    }

    public void listarUsuarios (){
        int i = 0;
        for (Map.Entry<Integer, Usuario> entry : listaUsuarios.entrySet()) {
            Usuario u = entry.getValue();
            System.out.println( i + ". " + u.getNombre() + " " + u.getApellido());
            i++;
        }
    }

    // ---------- ABM VUELO ----------

    public void agregarVuelo (Vuelo vuelo) {
        if(vuelo instanceof Vuelo)
            listaVuelos.put(vuelo.hashCode(), vuelo);
    }

    public void eliminarVuelo (Vuelo vuelo) {
        boolean existe = false;
        for (Map.Entry<Integer, Vuelo> entry : listaVuelos.entrySet()) {
            Vuelo v = (Vuelo) entry.getValue();
            if(v.equals(vuelo)){
                existe = true;
            }
        }

        if(existe){
            listaVuelos.remove(vuelo.hashCode());
        }
        else{
            System.out.println("El vuelo ingresado no se encuentra registrado.");
        }
    }

    public void listarVuelo (){
        int i = 0;
        for (Map.Entry<Integer, Vuelo> entry : listaVuelos.entrySet()) {
            Vuelo v = entry.getValue();
            System.out.println( i + ". " + v.getFecha() + " Origen: " + v.getOrigen() + " Destino: " + v.getDestino());
            i++;
        }
    }

    // ---------- ABM AVION ----------

    public void agregarAvion (Avion avion) {
        if(avion instanceof Avion)
            listaAviones.put(avion.hashCode(), avion);
    }

    public void eliminarAvion (Avion avion) {
        boolean existe = false;
        for (Map.Entry<Integer, Avion> entry : listaAviones.entrySet()) {
            Avion a = (Avion) entry.getValue();
            if(a.equals(avion)){
                existe = true;
            }
        }

        if(existe){
            listaAviones.remove(avion.hashCode());
        }
        else{
            System.out.println("El avion ingresado no se encuentra registrado.");
        }
    }

    public void listarAviones (){
        int i = 0;
        for (Map.Entry<Integer, Avion> entry : listaAviones.entrySet()) {
            Avion a = entry.getValue();
            System.out.println( i + ". " + a);
            i++;
        }
    }
}
