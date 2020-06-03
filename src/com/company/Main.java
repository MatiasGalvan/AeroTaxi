package com.company;

public class Main {

    public static void main(String[] args) {
        Usuario u1 = new Usuario("Juan", "Perez", 41999234, 26);
        Usuario u2 = new Usuario("Ramon", "Dominguez", 13359235, 49);
        Usuario u3 = new Usuario("Romina", "Jara", 30939254, 30);
        Usuario u4 = new Usuario("Lautaro", "Vidal", 30939254, 39);


        AeroTaxi a = new AeroTaxi();
        a.agregarUsuario(u1);
        a.agregarUsuario(u2);
        a.agregarUsuario(u3);

        a.listarUsuarios();

        a.eliminarUsuario(u4);
        a.eliminarUsuario(u2);

        a.listarUsuarios();

    }
}
