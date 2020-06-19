package com.company;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

public class Persistencia {

    private AeroTaxi sistema;


    public Persistencia(AeroTaxi sistema) {
        this.sistema = sistema;
    }

    public Persistencia() {

    }

    public void usuariosToArchivo() throws  IOException{
        GsonBuilder gson = new GsonBuilder();
        BufferedWriter bw = new BufferedWriter(new FileWriter("Usuarios.json"));
        try {
            String json = gson.create().toJson(sistema.getListaUsuarios());
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }
    public void archivoToUsuarios() throws  IOException{
        GsonBuilder gson = new GsonBuilder();
        BufferedReader br = new BufferedReader(new FileReader("Usuarios.json"));
        try {
             String jsonArray = br.readLine();
            System.out.println(jsonArray);
             Type listType = new TypeToken<LinkedList<Usuario>>(){}.getType();
             LinkedList<Usuario> json = gson.create().fromJson(jsonArray, listType);
            if(json != null)sistema.setListaUsuarios(json);
            sistema.listarUsuarios();
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

    public void vuelosToArchivo() throws  IOException{
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Avion.class, new AvionAdapter());
        BufferedWriter bw = new BufferedWriter(new FileWriter("Vuelos.json"));
        try {
            String json = gson.create().toJson(sistema.getListaVuelos());
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }

    public void archivoToVuelos() throws  IOException{
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Avion.class, new AvionAdapter());
        BufferedReader br = new BufferedReader(new FileReader("Vuelos.json"));
        try {
            String jsonArray = br.readLine();
            Type listType = new TypeToken<LinkedList<Vuelo>>(){}.getType();
            LinkedList<Vuelo> json = gson.create().fromJson(jsonArray, listType);
            if(json != null)sistema.setListaVuelos(json);
            sistema.listarVuelos();
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

    public void avionesToArchivo() throws  IOException{
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Avion.class, new AvionAdapter());
        BufferedWriter bw = new BufferedWriter(new FileWriter("Aviones.json"));
        try {
            String json = gson.create().toJson(sistema.getListaAviones());
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }
    public void archivoToAviones() throws  IOException{
        GsonBuilder gson = new GsonBuilder().registerTypeAdapter(Avion.class, new AvionAdapter());
        BufferedReader br = new BufferedReader(new FileReader("Aviones.json"));
        try {
            String jsonArray = br.readLine();
            System.out.println(jsonArray);
            Type listType = new TypeToken<LinkedList<Avion>>(){}.getType();
            LinkedList<Avion> json = gson.create().fromJson(jsonArray, listType);
            if(json != null)sistema.setListaAviones(json);
            sistema.listarAviones();
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

}
