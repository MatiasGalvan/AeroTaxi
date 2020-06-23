package com.company;

import com.company.exceptions.UsuarioNoExisteException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

import static com.company.Main.fechaActual;

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
        do {
            System.out.println(replicarCaracter(30, "-"));
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println(replicarCaracter(30, "-"));
            System.out.println("Que quiere hacer?: ");
            res = scan.nextInt();
            switch (res) {
                case 1:
                    ingresar();
                    break;
                case 2:
                    registrarse();
                    break;
            }
        } while(res != 3);
    }

    public void ingresar(){
        Scanner scan = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        String contraseña;
        boolean pass;
        int res=0, salir = 1;
        Usuario usuario = null;
        while(usuario == null && salir == 1){
            try{
                System.out.println("Ingrese DNI:");
                res = scanInt.nextInt();
                if(res != 0)
                    usuario = sistema.validarUsuario(res);
                else
                    salir = 0;
            }
            catch (UsuarioNoExisteException e){
                System.out.println("El DNI ingresado no se encuentra en el sistema. Intente nuevamente o ingrese 0 para salir.");
            }
        }

        if(salir != 0) {
            do {
                System.out.println("Ingrese contraseña:");
                contraseña = scan.nextLine();
                pass = contraseña.equals(usuario.getContraseña());
                if (!pass)
                    System.out.println("La contraseña es incorrecta.");
            } while (!pass);

            if (usuario.getDni() != 11000111) {
                menuUsuario(usuario);
            } else {
                menuAdmin();
            }
        }
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

    public String replicarCaracter(int cant, String a){
        String res = "";
        for (int i = 0; i < cant; i++) {
            res = res.concat(a);
        }
        return res;
    }

    public void menuUsuario(Usuario usuario) {
        int res = 0;
        int espacios = (30 - (usuario.getNombre().length() + usuario.getApellido().length() + 1)) / 2;
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("\n" + replicarCaracter(espacios, " ") + usuario.getNombre() + " " +
                    usuario.getApellido()+replicarCaracter(espacios, " "));
            System.out.println(replicarCaracter(30, "-"));
            System.out.println("1. Solicitar vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Ver reservas");
            System.out.println("4. Salir");
            System.out.println(replicarCaracter(30, "-"));
            System.out.println("Que quiere hacer?: ");
            res = scan.nextInt();
            switch (res) {
                case 1:
                    solicitarVuelo(usuario);
                    break;
                case 2:
                    cancelarVuelo(usuario);
                    break;
                case 3:
                    verReservas(usuario);
                    break;
            }
        } while(res != 4);
    }

    public void menuAdmin(){
        int res = 0;
        Scanner scanInt = new Scanner(System.in);
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println(replicarCaracter(30, "-"));
            System.out.println("1. Listar vuelos");
            System.out.println("2. Listar vuelos en determinada fecha");
            System.out.println("3. Listar aviones");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Agregar avion");
            System.out.println("6. Salir");
            System.out.println(replicarCaracter(30, "-"));
            System.out.println("Que quiere hacer?: ");
            res = scan.nextInt();
            switch (res) {
                case 1:
                    sistema.listarVuelos();
                    break;
                case 2:
                    sistema.listarVuelosPorFecha(solicitarFecha());
                    break;
                case 3:
                    sistema.listarAviones();
                    break;
                case 4:
                    sistema.listarUsuarios();
                    break;
                case 5:
                    Propulsion prop = null;
                    int pasajeros = 0;
                    int clase = 0;
                    Ciudad ciudad = null;

                    prop = seleccionarEnum(Propulsion.values(), "Indique que clase de propulsion utiliza el avion");

                    pasajeros = solicitarPasajeros();
;
                    ciudad = seleccionarEnum(Ciudad.values(), "Indique en que ciudad se encuentra el avion");

                    System.out.println("De que clase es el avion?:");
                    mostrarEnum(Clase.values(), "Clases disponibles");
                    clase = scanInt.nextInt();

                    crearAvion(pasajeros, prop, clase, ciudad);
                    break;
            }
        } while(res != 6);
    }

    public int solicitarPasajeros(){
        int pasajeros = 0;
        Scanner scanInt = new Scanner(System.in);
        boolean valido = false;
        while(!valido) {
            System.out.println("Indique cuantos pasajeros tiene el avion:");
            pasajeros = scanInt.nextInt();
            if(pasajeros > 0)
                valido = true;
            else
                System.out.println("La cantidad de pasajeros debe ser mayor a cero.");
        }
        return pasajeros;
    }

    public void crearAvion(int pasajeros, Propulsion prop, int clase, Ciudad ciudad){
        Avion av = null;
        switch (clase){
            case 0:
                av = new Bronze(pasajeros, prop, ciudad);
                break;
            case 1:
                av = new Silver(pasajeros, prop, ciudad);
                break;
            case 2:
                av = new Gold(pasajeros, prop, ciudad);
                break;
        }
        sistema.agregarAvion(av);
    }

    public void verReservas(Usuario usuario){
        LinkedList<UUID> reservas = usuario.getVuelosContratados();
        if(reservas.isEmpty()){
            System.out.println("Usted no tiene reservas.");
        }
        else {
            for (UUID reserva : reservas) {
                Vuelo v = sistema.buscarVueloPorID(reserva);
                System.out.println("Fecha: " + v.getFecha() + " Origen: " + v.getOrigen().getNombre() + " Destino: " + v.getDestino().getNombre() + " Clase: " + v.getClase());
            }
        }
    }

    public void cancelarVuelo(Usuario usuario){
        LinkedList<UUID> vuelos = usuario.getVuelosContratados();
        Vuelo v = null;
        int i = 0;
        boolean validar = false;
        Scanner scanInt = new Scanner(System.in);

        if(!vuelos.isEmpty()) {
            for (UUID vuelo : vuelos) {
                v = sistema.buscarVueloPorID(vuelo);
                System.out.println(i + ". " + v);
                i++;
            }
            System.out.println("Seleccione que vuelo desea cancelar:");
            while (!validar) {
                i = scanInt.nextInt();
                if (i >= 0 && i < vuelos.size()) {
                    validar = true;
                } else
                    System.out.println("Opcion no valida.");
            }

            v = sistema.buscarVueloPorID(vuelos.get(i));

            if (sistema.cancelarVuelo(v, usuario)) {
                System.out.println("El vuelo fue cancelado de su cuenta.");
            } else {
                System.out.println("No puede cancelarce el vuelo sin 24hs de anticipacion.");
            }
        }
        else{
            System.out.println("No tiene vuelos pendientes en su lista de reservas.");
        }
    }

    public void solicitarVuelo(Usuario usuario){
        Vuelo vuelo = new Vuelo();
        Scanner scanInt = new Scanner(System.in);
        boolean flag;

        vuelo.setFecha(solicitarFecha());
        vuelo.setOrigen(seleccionarEnum(Ciudad.values(), "Seleccione ciudad de origen"));

        do{
            vuelo.setDestino(seleccionarEnum(Ciudad.values(), "Seleccione ciudad de destino"));
            flag = sistema.validarVuelo(vuelo);
        }while(flag != true);

        vuelo.setCantPasajeros(solicitarPasajeros());
        vuelo.setClase(seleccionarEnum(Clase.values(), "Clases disponibles"));

        LinkedList<Vuelo> vuelos = sistema.vuelosSimilares(vuelo);
        LinkedList<Avion> aviones = sistema.buscarAvionesDisponibles(vuelo);

        if(!vuelos.isEmpty()){
            elegirVuelo(vuelos, usuario, vuelo.getCantPasajeros());
        }
        else if(!aviones.isEmpty()){
            elegirAvion(aviones, vuelo, usuario);
        }
        else{
            System.out.println("En este momento no se encuentran vuelos similares, ni aviones disponibles");
        }
    }

    public void elegirAvion(LinkedList<Avion> aviones, Vuelo vuelo, Usuario usuario){
        int i = 0, aux = 0;
        Scanner scanInt = new Scanner(System.in);
        System.out.println("Aviones disponibles encontrados: ");
        for (Avion avion : aviones) {
            System.out.println(i + ". Clase: " + avion.getClase() +
                    " Tarifa de avion: " + avion.getTarifa() +
                    " Servicio de catering: " + avion.isServicioCatering());
            i++;
        }
        System.out.println("¿Quiere avion quiere contratar?: ");
        aux = scanInt.nextInt();
        if(aux >= 0 && aux < aviones.size()) {
            Avion a = aviones.get(aux);
            vuelo.setAvion(a);
            vuelo.setCostoTotal();
            System.out.println("El costo del vuelo es de: " + vuelo.getCostoTotal());
            System.out.println("Ingrese 1 para confirmar la reserva, o 0 para cancelar la operacion.");
            aux = scanInt.nextInt();
            if(aux == 1) {
                sistema.agregarVuelo(vuelo, usuario);
                System.out.println("Reserva completada");
            }
        }
        else
            System.out.println("Opcion no valida");
    }

    public void elegirVuelo(LinkedList<Vuelo> vuelos, Usuario usuario, int cantPasajeros){
        int i = 0, aux = 0;
        Scanner scanInt = new Scanner(System.in);
        System.out.println("Vuelos similares encontrados: ");
        for (Vuelo v : vuelos) {
            System.out.println(i + ". Origen: " + v.getOrigen().getNombre() +
                    " Destino: " + v.getDestino().getNombre() +
                    " Fecha: " + v.getFecha() +
                    " Costo: " + v.costoPorCant(cantPasajeros) +
                    " Clase: " + v.getAvion().getClase());
            i++;
        }
        System.out.println("¿Que vuelo quiere elegir?: ");
        aux = scanInt.nextInt();
        if(aux >= 0 && aux < vuelos.size()) {
            Vuelo v = vuelos.get(aux);
            sistema.agregarVueloExistente(v, usuario, cantPasajeros);
            System.out.println("Reserva completada");
        }
        else
            System.out.println("Opcion no valida");
    }

    public LocalDate solicitarFecha(){
        Scanner scanInt = new Scanner(System.in);
        int dia=0, mes=0, año=0;
        LocalDate fecha = null;
        boolean validar = false, aux = false;

        while (!validar) {
            try {
                System.out.println("FECHA:");
                System.out.println("Ingrese dia:");
                dia = scanInt.nextInt();
                System.out.println("Ingrese mes:");
                mes = scanInt.nextInt();
                while (!aux) {
                    System.out.println("Ingrese año:");
                    año = scanInt.nextInt();
                    if(año >= fechaActual().getYear() && año <= (fechaActual().getYear()+10))
                        aux = true;
                    else{
                        System.out.println("Solo pueden realizarse reservas entre el año "+fechaActual().getYear()+
                                " - "+(fechaActual().getYear()+10));
                    }
                }
                fecha = LocalDate.of(año,mes,dia);
                validar = true;
            } catch (DateTimeException e) {
                System.out.println("\nLa fecha ingresada no es valida. Dia 1-31 Mes 1-12\n");
                aux = false;
            }
        }

        return fecha;
    }

    public <T extends Enum<T>> T seleccionarEnum(T [] a, String titulo){
        Scanner scanInt = new Scanner(System.in);
        int id;
        T respuesta = null;
        boolean valido = false;

        while(!valido) {
            mostrarEnum(a, titulo);
            System.out.println("Que opcion desea elegir?: ");
            id = scanInt.nextInt();

            if (id >= 0 && id < a.length) {
                respuesta = a[id];
                valido = true;
            }
            else{
                System.out.println("El ID ingresado no es valido.");
            }
        }
        return respuesta;
    }

    public <T extends Enum<T>> void mostrarEnum(T [] a, String titulo){
        int i = 0;
        System.out.println(titulo);
        System.out.println("-----------------------");
        for (T obj : a) {
            System.out.println(i + ". " + obj);
            i++;
        }
        System.out.println("-----------------------");
    }
}
