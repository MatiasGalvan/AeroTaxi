package com.company;

import java.util.Scanner;

public class Menu {

    private AeroTaxi sistema;

    public Menu(AeroTaxi sistema) {
        this.sistema = sistema;
    }

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
                ingresar();
                break;
            case 2:
                registrarse();
                break;
            case 3:
                salir();
                break;
        }
    }

    public void ingresar(){
        Scanner scan = new Scanner(System.in);
        int res=0;
        String contraseña="";
        Usuario usuario = new Usuario();
        do {
            System.out.println("Ingrese DNI:");
            res = scan.nextInt();
            usuario= sistema.validarUsuario(res);
            if(usuario == null) System.out.println("El DNI ingresado no se encuentra en el sistema");
        }while(usuario == null);

        do {
            System.out.println("Ingrese contraseña:");
            contraseña = scan.nextLine();
            if(contraseña!= usuario.getContraseña()) System.out.println("La contraseña es incorrecta");
        }while(contraseña != usuario.getContraseña());
        menuUsuario();
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
        System.out.println("Ingrese contraseña:");
        usuario.setContraseña(scan.nextLine());
        sistema.agregarUsuario(usuario);
    }

    public void salir(){
        System.exit(0);
    }

    public void menuUsuario() {
        Scanner scan = new Scanner(System.in);
        int res;
        System.out.println("------------------------------");
        System.out.println("1. Solicitar vuelo");
        System.out.println("2. Cancelar vuelo");
        System.out.println("3. Salir");
        System.out.println("------------------------------");
        System.out.println("Que quiere hacer?: "); res = scan.nextInt();
        switch (res) {
            case 1:
                //solicitar vuelo
                break;
            case 2:
                //cancelar vuelo
                break;
            case 3:
                salir();
                break;
        }
    }
}
