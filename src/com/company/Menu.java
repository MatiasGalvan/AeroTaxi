package com.company;

import java.util.Scanner;

public class Menu {

    AeroTaxi sistema;

    public Menu(AeroTaxi sistema) {
        this.sistema = sistema;
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
            case 3:
                salir();
                break;
        }
    }

    public void registrarse () {
        Usuario usuario = new Usuario();
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese nombre:");
        usuario.setNombre(scan.nextLine());
        System.out.println("Ingrese apellido:");
        usuario.setApellido(scan.nextLine());
        System.out.println("Ingrese DNI:");
        usuario.setDni(scan.nextInt());
        System.out.println("Ingrese edad:");
        usuario.setEdad(scan.nextInt());
        sistema.agregarUsuario(usuario);
    }

    public void salir(){
        System.exit(0);
    }

}
