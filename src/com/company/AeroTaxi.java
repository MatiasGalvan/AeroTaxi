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

    /*
    * public void agregarVuelo (Vuelo vuelo)
    * public void eliminarVuelo (Vuelo vuelo)
    * public void listarVuelos ()
    *
    * public void agregarAvion (Avion avion)
    * public void eliminarAvion (Avion avion)
    * public void listarAviones ()
    * */
}
