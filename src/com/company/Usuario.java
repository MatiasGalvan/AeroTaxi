package com.company;

import java.util.LinkedList;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private UUID mejorAvionUsado; //ID del avion
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

    public UUID getMejorAvionUsado() {
        return mejorAvionUsado;
    }

    public void setMejorAvionUsado(UUID mejorAvionUsado) {
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

    public void agregarVueloContratado(UUID vuelo){
        this.vuelosContratados.add(vuelo);
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
