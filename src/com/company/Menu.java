package com.company;

import com.company.exceptions.UsuarioNoExisteException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

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
        boolean pass;
        int res=0;
        Usuario usuario = null;
        while(usuario == null){
            try{
                System.out.println("Ingrese DNI:");
                res = scanInt.nextInt();
                usuario = sistema.validarUsuario(res);
            }
            catch (UsuarioNoExisteException e){
                System.out.println("El DNI ingresado no se encuentra en el sistema.");
            }
        }

        do{
            System.out.println("Ingrese contraseña:");
            contraseña = scan.nextLine();
            pass = contraseña.equals(usuario.getContraseña());
            if(!pass)
                System.out.println("La contraseña es incorrecta");
        } while(!pass);

        menuUsuario(usuario);
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

    public void menuUsuario(Usuario usuario) {
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
                solicitarVuelo(usuario);
                break;
            case 2:
                //cancelar vuelo
                break;
            case 3:
                salir();
                break;
        }
    }

    public void cancelarVuelo(Usuario usuario){
        LinkedList<UUID> vuelos = usuario.getVuelosContratados();
        Vuelo v = null;
        int i = 0;
        Scanner scanInt = new Scanner(System.in);
        for (UUID vuelo : vuelos) {
            v = sistema.buscarVueloPorID(vuelo);
            System.out.println(i + ". " + v);
        }
        System.out.println("Seleccione que vuelo desea cancelar:");
        i = scanInt.nextInt();
        v = sistema.buscarVueloPorID(vuelos.get(i));
        sistema.cancelarVuelo(v, usuario);
    }

    public void solicitarVuelo(Usuario usuario){
        Vuelo vuelo = new Vuelo();
        Scanner scanInt = new Scanner(System.in);
        boolean flag;
        int idOrigen=0, idDestino=0, dia=0, mes=0, año=0, i=0;

        System.out.println("FECHA:");
        System.out.println("Ingrese dia:");
        dia = scanInt.nextInt();
        System.out.println("Ingrese mes:");
        mes = scanInt.nextInt();
        System.out.println("Ingrese año:");
        año = scanInt.nextInt();
        LocalDate fecha = LocalDate.of(año,mes,dia);
        vuelo.setFecha(fecha);

        mostrarCiudades();
        System.out.println("Seleccione ciudad de origen:");
        idOrigen = scanInt.nextInt();
        vuelo.setOrigen(seleccionar(idOrigen));

        do{
            mostrarCiudades();
            System.out.println("Seleccione ciudad de destino:");
            idDestino = scanInt.nextInt();
            vuelo.setDestino(seleccionar(idDestino));
            flag = sistema.validarVuelo(vuelo);
        }while(flag != true);

        System.out.println("Indique cantidad de pasajeros:");
        vuelo.setCantPasajeros(scanInt.nextInt());

        LinkedList<Vuelo> vuelos = sistema.vuelosSimilares(vuelo);
        LinkedList<Avion> aviones = sistema.buscarAvionesDisponibles(vuelo);

        if(!vuelos.isEmpty()){
            System.out.println("Vuelos similares encontrados: ");
            for (Vuelo v : vuelos) {
                System.out.println(i + ". Origen: " + v.getOrigen().getNombre() +
                        " Destino: " + v.getDestino().getNombre() +
                        " Fecha: " + v.getFecha() +
                        " Costo total: " + v.getCostoTotal() +
                        " Clase: " + v.getAvion().getClass().getName());
                i++;
            }
            System.out.println("¿Quiere elegir un vuelo o abrir uno nuevo?: ");
        }
        if(!aviones.isEmpty()){
            i = 0;
            System.out.println("Aviones disponibles encontrados: ");
            for (Avion avion : aviones) {
                System.out.println(i + ". Clase: " + avion.getClass().getName() +
                        " Tarifa de avion: " + avion.getTarifa());
                i++;
            }
            System.out.println("¿Quiere avion quiere contratar?: ");
        }

        //usuario.agregarVueloContratado(vuelo.getId());

        System.out.println(vuelo);
    }

    public Ciudad seleccionar(int id){
        Ciudad ciudades[] = Ciudad.values();
        Ciudad respuesta = null;
        if(id >= 0 && id < ciudades.length)
            respuesta = ciudades[id];
        return respuesta;
    }

    public void mostrarCiudades(){
        int i=0;
        System.out.println("Ciudades disponibles:");
        System.out.println("-----------------------");
        for(Ciudad value: Ciudad.values()){
            System.out.println(i+". "+value.getNombre());
            i++;
        }
        System.out.println("-----------------------");
    }
}
