package com.company;

import java.time.LocalDate;
import java.util.LinkedList;

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

        Bronze av1 = new Bronze(100,100,1000,Propulsion.HELICE,Ciudad.BSAS);
        Silver av2 = new Silver(250,50,2000,Propulsion.PISTON,Ciudad.CORDOBA);
        Gold av3 = new Gold(500,20,3000,Propulsion.REACCION,Ciudad.MONTEVIDEO);
        Silver av4 = new Silver(250,25,2000,Propulsion.PISTON,Ciudad.CORDOBA);
        Gold av5 = new Gold(500,10,3000,Propulsion.REACCION,Ciudad.SANTIAGO);

        a.agregarAvion(av1);
        a.agregarAvion(av2);
        a.agregarAvion(av3);
        a.agregarAvion(av4);
        a.agregarAvion(av5);

        a.listarAviones();

        LocalDate fecha = LocalDate.of(2020,6,10);
        LocalDate fecha2 = LocalDate.of(2020,6,11);
        LocalDate fecha3 = LocalDate.of(2020,6,12);
        Vuelo v1 = new Vuelo(Ciudad.CORDOBA,Ciudad.BSAS,5,fecha);
        Vuelo v2 = new Vuelo(Ciudad.BSAS,Ciudad.SANTIAGO,5,fecha2);
        Vuelo v3 = new Vuelo(Ciudad.SANTIAGO,Ciudad.MONTEVIDEO,5,fecha3);

        v1.setAvion(av2);
        v2.setAvion(av1);

        av1.agregarReserva(v2);
        av2.agregarReserva(v1);

        a.agregarVuelo(v1);
        a.agregarVuelo(v2);

        a.listarVuelos();

        System.out.println("\n\n");

        LinkedList<Avion> disponibles = a.buscarAvionesDisponibles(v3);

        for (Avion avion : disponibles) {
            System.out.println(avion);
        }


        System.out.println(Ciudad.distanciaKM(Ciudad.SANTIAGO, Ciudad.CORDOBA));
        System.out.println(Ciudad.distanciaKM(Ciudad.SANTIAGO, Ciudad.BSAS));
        System.out.println(Ciudad.distanciaKM(Ciudad.CORDOBA, Ciudad.SANTIAGO));

        System.out.println("Tarifa: " + av1.getTarifa() + " Costo por km: " + av1.getCostoKm() + " CantPasajeros: " + v2.getCantPasajeros());
        System.out.println(v2.calcularCosto());
        /*
        av5.agregarReserva(v1);
        av5.agregarReserva(v3);
        if(av5.disponibilidad(v2)){
            av5.agregarReserva(v2);
        }
        else{
            System.out.println("No padre");
        }

        av5.listarReservas();*/

        /*
        LocalDate actual = LocalDate.of(2020,6,10);
        LocalDate date = actual;
        LocalDate date2 = actual;
        date = date.minusDays(1);
        System.out.println(date);

        date = date.plusDays(1);
        if(date.isEqual(date2)){
            System.out.println("rE");
        }*/

        /*Menu m = new Menu();
        m.inicio();*/

    }

    public static LocalDate fechaActual(){
        return LocalDate.of(2020,6,8);
    }
}
