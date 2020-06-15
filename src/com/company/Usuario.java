package com.company;

import java.util.LinkedList;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private Clase mejorAvionUsado; //ID del avion
    private int dni;
    private int edad;
    private double totalGastado;
    private String nombre;
    private String apellido;
    private String contraseña;
    private LinkedList<UUID> vuelosContratados;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int dni, int edad, String contraseña) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.mejorAvionUsado = null;
        this.totalGastado = 0;
        this.contraseña = contraseña;
        this.vuelosContratados = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Clase getMejorAvionUsado() {
        return mejorAvionUsado;
    }

    public void setMejorAvionUsado(Clase mejorAvionUsado) {
        this.mejorAvionUsado = mejorAvionUsado;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
    }

    public UUID getId() {
        return id;
    }

    public void mejorAvion(Clase clase){
        Clase clases[] = Clase.values();

        int base = valorClase(clases, this.mejorAvionUsado);
        int nueva = valorClase(clases, clase);
        if (nueva > base) {
            mejorAvionUsado = clases[nueva];
        }
    }

    public int valorClase(Clase clases[], Clase clase){
        int res = -1, i = 0;
        while(i < clases.length && res == -1){
            if (clases[i].equals(clase)) {
                res = i;
            }
            i++;
        }
        return res;
    }

    public void agregarVueloContratado(Vuelo vuelo){
        this.vuelosContratados.add(vuelo.getId());
    }

    public void eliminarVueloContratado(UUID vuelo){
        int i = 0;
        boolean flag = false;

        while(i < vuelosContratados.size() && flag == false){
            if( vuelosContratados.get(i).equals(vuelo)){
                this.vuelosContratados.remove(vuelo);
                flag = true;
            }
        }
    }

    public void gastado(double costo){
        totalGastado += costo;
    }

    public LinkedList<UUID> getVuelosContratados() {
        return vuelosContratados;
    }

    @Override
    public String toString() {
        return "\nNombre: '" + nombre + '\'' +
                "\nApellido: '" + apellido + '\'' +
                "\nDNI: " + dni +
                "\nEdad: " + edad +
                "\nMejor avion usado: " + mejorAvionUsado +
                "\nTotal gastado: " + totalGastado +
                "\ncontraseña: " + contraseña;
    }
}
