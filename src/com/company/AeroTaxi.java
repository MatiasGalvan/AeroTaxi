package com.company;

import java.util.LinkedList;
import java.util.Map;

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

    public Usuario validarUsuario(int dni){
        Usuario usuario = new Usuario();
        int i =0;
        while(i < listaUsuarios.size() && dni != usuario.getDni()){
            if(dni == listaUsuarios.get(i).getDni()){
                usuario = listaUsuarios.get(i);
            }
        }
        return usuario;
    }


    // ---------- ABM VUELO ----------

    public void agregarVuelo (Vuelo vuelo) {
        if(validarVuelo(vuelo))
            listaVuelos.add(vuelo);
    }

    public void eliminarVuelo (Vuelo vuelo) {
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

    public void listarAvionesDisponibles (Vuelo vuelo){

    }
}
