/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainfutbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mint
 */
public class Torneo {
    
    private String nombre;
    private String ciudad;
    private String fecha;

    private Map<String, Equipo> equipos;
    private Map<Equipo, Jugador> capitanes;
    
    public Torneo(String nombre, String ciudad, String fecha) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fecha = fecha;
        
        equipos = new HashMap<>();
        capitanes = new HashMap<>();
    }
    
    public Torneo(String sCSV){
        String[] lineas = sCSV.split("\n");
        String[] columnas = lineas[0].split(":");
        if (columnas[0].equals("TORNEO")) {
            String[] datos = columnas[1].split(";");
            this.nombre = datos[0];
            this.ciudad = datos[1];
            this.fecha = datos[2];
        } else {
            return;
        }
        
        this.equipos = new HashMap<>();

        String[] posiblesEquipos = sCSV.split("EQUIPO");
        String equipoCSV;

        for (int i = 1; i < posiblesEquipos.length; i++) {
            equipoCSV = "EQUIPO" + posiblesEquipos[i];
            Equipo e = new Equipo(equipoCSV);
            equipos.put(e.getNombre(), e);
        }
        
        capitanes = new HashMap<>();
    }
    
    public void generarCapitanes() {

        //Guardo todos los equipos en un Array
        ArrayList<Equipo> listaEquipos = new ArrayList<>(equipos.values());
        //Creo un array de jugadores para poder ordenarlos
        ArrayList<Jugador> listaJugadores = null;
        for (Equipo equipo : listaEquipos) {
            //Para un equipo, ordeno sus jugadores y añado el primero como capitan
            listaJugadores = new ArrayList<>(equipo.getJugadores().values());
            Collections.sort(listaJugadores, new ComparatorGoles());
            capitanes.put(equipo,listaJugadores.get(0));
        }                          
    }
    
    public boolean addEquipo(Equipo e){
        Equipo antiguo = equipos.put(e.getNombre(), e);
        
        if (antiguo != null) {
            equipos.put(antiguo.getNombre(), antiguo);
            return false;
        }        
        return true;
    }
    
    public Equipo getEquipo(String nombre){
        for (Map.Entry<String, Equipo> entry : equipos.entrySet()) {
            //Esto es un forEach para HashMap
            Equipo e = entry.getValue();
            if(e.getNombre().equals(nombre)){
                return e;
            }
        }
        return null;
    }
    
    public ArrayList<Jugador> mostrarCapitanes(){
        this.generarCapitanes();
        ArrayList<Jugador> misCapitanes = new ArrayList<>();
        
        if(capitanes.isEmpty()){
            return null;
        }
        
        for (Map.Entry<Equipo, Jugador> entry : capitanes.entrySet()) {
            //Esto es un forEach para HashMap
            Jugador j = entry.getValue();
            misCapitanes.add(j);
        }

        return misCapitanes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Map<String, Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(Map<String, Equipo> equipos) {
        this.equipos = equipos;
    }

    public Map<Equipo, Jugador> getCapitanes() {
        return capitanes;
    }

    public void setCapitanes(Map<Equipo, Jugador> capitanes) {
        this.capitanes = capitanes;
    }
    
    
    
    @Override
    public String toString() {
        String cadena;
        cadena = String.format("TORNEO:%s;%s;%s\n", nombre, ciudad, fecha);
        for (Map.Entry<String, Equipo> entry : equipos.entrySet()) {
            //Esto es un forEach para HashMap
            //Paso a CSV este objeto.
            cadena += entry.getValue().toString();
        }
        
        return cadena;
    }

    
    
}
