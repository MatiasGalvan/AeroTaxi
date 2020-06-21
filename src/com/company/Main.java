package com.company;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

       // Bronze av1 = new Bronze(100,100,1000,Propulsion.HELICE,Ciudad.BSAS);
        //Silver av2 = new Silver(250,50,2000,Propulsion.PISTON,Ciudad.CORDOBA);
        //Gold av3 = new Gold(500,20,3000,Propulsion.REACCION,Ciudad.MONTEVIDEO);
        AeroTaxi a = new AeroTaxi();
        //a.agregarAvion(av1);
        //a.agregarAvion(av2);
        //a.agregarAvion(av3);
        Persistencia p = new Persistencia(a);
        p.archivoToUsuarios();
        p.archivoToAviones();
        p.archivoToVuelos();
        a.listarUsuarios();
        a.listarAviones();
        a.listarVuelos();





       /* LocalDate fecha = LocalDate.of(2020,6,10);
        LocalDate fecha2 = LocalDate.of(2020,6,11);
        LocalDate fecha3 = LocalDate.of(2020,6,12);

        Vuelo v1 = new Vuelo(Ciudad.CORDOBA,Ciudad.BSAS,5,fecha);
        Vuelo v2 = new Vuelo(Ciudad.BSAS,Ciudad.SANTIAGO,5,fecha2);
        v1.setAvion(av1);
        v2.setAvion(av2);
        v1.setCostoTotal();
        v2.setCostoTotal();
        v1.setClase(av2.getClase());
        v2.setClase(av1.getClase());
        a.cargarVuelo(v1);
        a.cargarVuelo(v2);
        a.listarVuelos();*/
        a.actualizar();
        Menu m = new Menu(a);
        m.inicio();

        p.usuariosToArchivo();
        p.vuelosToArchivo();
        p.avionesToArchivo();

    }

    public static LocalDate fechaActual(){
        return LocalDate.of(2020,6,12);
    }
}
