package com.company;

import com.company.exceptions.UsuarioNoExisteException;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Scanner scanInt = new Scanner(System.in);
        String contraseña;
        int res=0;
        Usuario usuario = null;
        while(usuario == null){
            try{
                System.out.println("Ingrese DNI:");
                res = scanInt.nextInt();
                usuario= sistema.validarUsuario(res);
                }catch (UsuarioNoExisteException e){
                System.out.println("El DNI ingresado no se encuentra en el sistema.");
             }
        }
        String prueba = "mauroemmi24";
        String contraseña2="";
        do{
            System.out.println("Ingrese contraseña:");
            contraseña = scan.nextLine();
            if(!contraseña.equals(usuario.getContraseña())) System.out.println("La contraseña es incorrecta");
        }while(!contraseña.equals(usuario.getContraseña()));

        menuUsuario();
    }

    public void registrarse () {
        Usuario usuario = new Usuario();
        Scanner scan = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        System.out.println("Ingrese nombre:");
        usuario.setNombre(scan.nextLine());
        System.out.println("Ingrese apellido:");
        usuario.setApellido(scan.nextLine());
        System.out.println("Ingrese DNI:");
        usuario.setDni(scanInt.nextInt());
        System.out.println("Ingrese edad:");
        usuario.setEdad(scanInt.nextInt());
        System.out.println("Ingrese contraseña:");
        usuario.setContraseña(scan.nextLine());
        sistema.agregarUsuario(usuario);
        inicio();
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
                solicitarVuelo();
                break;
            case 2:
                //cancelar vuelo
                break;
            case 3:
                salir();
                break;
        }
    }
    public void solicitarVuelo(){
        Vuelo vuelo = new Vuelo();
        mostrarCiudades();
        System.out.println("Seleccione ciudad de origen:");
        
    }
    public void mostrarCiudades(){
        System.out.println("Ciudades disponibles:");
        System.out.println("-----------------------");
        for(Ciudad value: Ciudad.values()){
            System.out.println("Ciudad="+value.name());
        }
        System.out.println("-----------------------");
    }
}
