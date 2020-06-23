package com.company;

import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws IOException {
        Usuario admin = new Usuario("Admin","Sistema",11000111,35,"1234");
        AeroTaxi a = new AeroTaxi();
        a.agregarUsuario(admin);
        Persistencia p = new Persistencia(a);
        p.archivoToUsuarios();
        p.archivoToBronze();
        p.archivoToSilver();
        p.archivoToGold();
        p.archivoToVuelos();


        a.actualizar();
        Menu m = new Menu(a);
        m.inicio();

        p.usuariosToArchivo();
        p.bronzeToArchivo();
        p.silverToArchivo();
        p.goldToArchivo();
        p.vuelosToArchivo();

    }

    public static LocalDate fechaActual(){
        return LocalDate.of(2020,6,20);
    }
}
