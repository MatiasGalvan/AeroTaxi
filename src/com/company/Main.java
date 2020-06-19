package com.company;

import java.time.LocalDate;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Usuario u1 = new Usuario("Juan", "Perez", 41999234, 26, "1234");
        Usuario u2 = new Usuario("Ramon", "Dominguez", 13359235, 49, "1234");
        Usuario u3 = new Usuario("Romina", "Jara", 30939254, 30, "1234");
        Usuario u4 = new Usuario("Lautaro", "Vidal", 30956254, 39, "1234");
        Usuario u5 = new Usuario("Admin", "1234", 11000111, 20, "1234");

        AeroTaxi a = new AeroTaxi();
        a.agregarUsuario(u1);
        a.agregarUsuario(u2);
        a.agregarUsuario(u3);
        a.agregarUsuario(u4);
        a.agregarUsuario(u5);

        Avion av1 = new Bronze(1000, Propulsion.HELICE,Ciudad.BSAS);
        Avion av2 = new Silver(250, Propulsion.PISTON,Ciudad.CORDOBA);
        Avion av3 = new Gold(500, Propulsion.REACCION,Ciudad.MONTEVIDEO);
        Avion av4 = new Silver(250, Propulsion.PISTON,Ciudad.CORDOBA);
        Avion av5 = new Gold(50, Propulsion.REACCION,Ciudad.SANTIAGO);

        a.agregarAvion(av1);
        a.agregarAvion(av2);
        a.agregarAvion(av3);
        a.agregarAvion(av4);
        a.agregarAvion(av5);
        //a.listarAviones();

        LocalDate fecha = LocalDate.of(2020,6,10);
        LocalDate fecha2 = LocalDate.of(2020,6,11);
        LocalDate fecha3 = LocalDate.of(2020,6,12);
        Vuelo v1 = new Vuelo(Ciudad.CORDOBA,Ciudad.BSAS,5,fecha);
        Vuelo v2 = new Vuelo(Ciudad.BSAS,Ciudad.SANTIAGO,5,fecha2);
        Vuelo v3 = new Vuelo(Ciudad.BSAS,Ciudad.SANTIAGO,5,fecha2);

        v1.setAvion(av2);
        v2.setAvion(av1);
        v1.setCostoTotal();
        v2.setCostoTotal();
        v1.setClase(av2.getClase());
        v2.setClase(av1.getClase());

        a.agregarVuelo(v1, u1);
        a.agregarVuelo(v2, u2);

       // a.listarVuelos();

        a.actualizar();

        /*a.listarUsuarios();
        a.listarVuelos();
        a.listarAviones();*/

        Menu m = new Menu(a);
        m.inicio();

        /*a.listarUsuarios();
        a.listarVuelos();
        a.listarAviones();
        a.listarVuelosPorFecha(fecha2);*/
    }

    public static LocalDate fechaActual(){
        return LocalDate.of(2020,6,20);
    }
}
