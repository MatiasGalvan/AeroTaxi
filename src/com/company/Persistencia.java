package com.company;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.time.LocalDate;
public class Persistencia {

    private AeroTaxi sistema;


    public Persistencia(AeroTaxi sistema) {
        this.sistema = sistema;
    }

    public Persistencia() {

    }

    public void usuariosToArchivo() throws  IOException{
        Gson gson = new Gson();
        BufferedWriter bw = new BufferedWriter(new FileWriter("Usuarios.json"));
        try {
            String json = gson.toJson(sistema.getListaUsuarios());
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
             Type listType = new TypeToken<LinkedList<Usuario>>(){}.getType();
             LinkedList<Usuario> json = gson.create().fromJson(jsonArray, listType);
            if(json != null)sistema.setListaUsuarios(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

    public void vuelosToArchivo() throws  IOException{
        RuntimeTypeAdapterFactory<Avion> avionAdapterFactory = RuntimeTypeAdapterFactory.of(Avion.class, "type")
                .registerSubtype(Bronze.class, "Bronze")
                .registerSubtype(Silver.class, "Silver")
                .registerSubtype(Gold.class, "Gold");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(avionAdapterFactory).create();
        BufferedWriter bw = new BufferedWriter(new FileWriter("Vuelos.json"));
        try {
            String json = gson.toJson(sistema.getListaVuelos());
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }

    public void archivoToVuelos() throws  IOException{
        RuntimeTypeAdapterFactory<Avion> avionAdapterFactory = RuntimeTypeAdapterFactory.of(Avion.class, "type")
                .registerSubtype(Bronze.class, "Bronze")
                .registerSubtype(Silver.class, "Silver")
                .registerSubtype(Gold.class, "Gold");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(avionAdapterFactory).create();
        BufferedReader br = new BufferedReader(new FileReader("Vuelos.json"));
        try {
            String jsonArray = br.readLine();
            Type listType = new TypeToken<LinkedList<Vuelo>>(){}.getType();
            LinkedList<Vuelo> json = gson.fromJson(jsonArray, listType);
            if(json != null)sistema.setListaVuelos(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

    public void avionesToArchivo() throws  IOException{
        RuntimeTypeAdapterFactory<Avion> avionAdapterFactory = RuntimeTypeAdapterFactory.of(Avion.class, "type")
                .registerSubtype(Bronze.class, "Bronze")
                .registerSubtype(Silver.class, "Silver")
                .registerSubtype(Gold.class, "Gold");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(avionAdapterFactory).create();
        BufferedWriter bw = new BufferedWriter(new FileWriter("Aviones.json"));
        try {
            for(Avion avion:sistema.getListaAviones()){
                String json = gson.toJson(avion,Avion.class);
                bw.write(json);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }
    public void archivoToAviones() throws  IOException{
        RuntimeTypeAdapterFactory<Avion> avionAdapterFactory = RuntimeTypeAdapterFactory.of(Avion.class, "type")
                .registerSubtype(Bronze.class, "Bronze")
                .registerSubtype(Silver.class, "Silver")
                .registerSubtype(Gold.class, "Gold");
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(avionAdapterFactory).create();
        Type avionType = new TypeToken<LinkedList<Avion>>(){}.getType();
        BufferedReader br = new BufferedReader(new FileReader("Aviones.json"));
        try {
            String json;
            while((json = br.readLine()) != null){
                Avion avion = gson.fromJson(json, Avion.class);
                sistema.agregarAvion(avion);
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }

    }

}
