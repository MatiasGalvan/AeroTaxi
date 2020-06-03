package com.company;

import java.util.Scanner;

public class Menu {

    public Menu() {
    }

    public void inicio () {
        Scanner scan = new Scanner(System.in);
        int res = 0;
        System.out.println("------------------------------");
        System.out.println("1. Ingresar");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.println("------------------------------");
        System.out.println("Que quiere hacer?: "); res = scan.nextInt();
        switch (res) {
            case 1:
                break;
            case 2:
                registrarse();
                break;
        }
    }

    public void registrarse () {

    }


}
