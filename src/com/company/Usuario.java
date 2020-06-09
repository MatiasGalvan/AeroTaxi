package com.company;

public class Usuario {
    private String nombre;
    private String apellido;
    private int dni;
    private int edad;
    private Avion mejorAvionUsado;
    private double totalGastado;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, int dni, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.mejorAvionUsado = null;
        this.totalGastado = 0;
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

    public Avion getMejorAvionUsado() {
        return mejorAvionUsado;
    }

    public void setMejorAvionUsado(Avion mejorAvionUsado) {
        this.mejorAvionUsado = mejorAvionUsado;
    }

    public double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(double totalGastado) {
        this.totalGastado = totalGastado;
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
