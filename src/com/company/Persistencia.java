package com.company;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

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
        Gson gson = new Gson();
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
        Gson gson = new Gson();
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

    public void bronzeToArchivo() throws IOException {
        Gson gson = new Gson();
        BufferedWriter bw = new BufferedWriter(new FileWriter("AvionesBronze.json"));
        ArrayList aviones = new ArrayList();
        try {
            String json="";
            for (Avion avion: sistema.getListaAviones()){
                if(avion instanceof Bronze) aviones.add(avion);
            }
            json = gson.toJson(aviones);
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }

    public void archivoToBronze() throws  IOException{
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("AvionesBronze.json"));
        try {
            String jsonArray = br.readLine();
            Type listType = new TypeToken<LinkedList<Bronze>>(){}.getType();
            LinkedList<Bronze> aviones = gson.fromJson(jsonArray,listType);
            if(aviones != null){
                for (Bronze avion:aviones){
                    sistema.agregarAvion(avion);
                }
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

    public void silverToArchivo() throws IOException {
        Gson gson = new Gson();
        BufferedWriter bw = new BufferedWriter(new FileWriter("AvionesSilver.json"));
        ArrayList aviones = new ArrayList();
        try {
            String json="";
            for (Avion avion: sistema.getListaAviones()){
                if(avion instanceof Silver) aviones.add(avion);
            }
            json = gson.toJson(aviones);
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }
    public void archivoToSilver() throws  IOException{
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("AvionesSilver.json"));
        try {
            String jsonArray = br.readLine();
            Type listType = new TypeToken<LinkedList<Silver>>(){}.getType();
            LinkedList<Silver> aviones = gson.fromJson(jsonArray,listType);
            aviones = gson.fromJson(jsonArray, listType);
            if(aviones != null){
                for (Silver avion:aviones){
                    sistema.agregarAvion(avion);
                }
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

    public void goldToArchivo() throws IOException {
        Gson gson = new Gson();
        BufferedWriter bw = new BufferedWriter(new FileWriter("AvionesGold.json"));
        ArrayList aviones = new ArrayList();
        try {
            String json="";
            for (Avion avion: sistema.getListaAviones()){
                if(avion instanceof Gold) aviones.add(avion);
            }
            json = gson.toJson(aviones);
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo:" + e.getMessage());
        } finally {
            bw.close();
        }
    }
    public void archivoToGold() throws  IOException{
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("AvionesGold.json"));
        try {
            String jsonArray = br.readLine();
            Type listType = new TypeToken<LinkedList<Gold>>(){}.getType();
            LinkedList<Gold> aviones = gson.fromJson(jsonArray,listType);
            aviones = gson.fromJson(jsonArray, listType);
            if(aviones != null){
                for (Gold avion:aviones){
                    sistema.agregarAvion(avion);
                }
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer en el archivo:" + e.getMessage());
        } finally {
            br.close();
        }
    }

}
