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
public class Equipo {

    private String nombre;
    private int puntuacion;
    private String ciudad;
    private String email;

    private Map<String, Jugador> jugadores;
    private Portero[] porteros;

    private boolean activo;

    public Equipo(String nombre, String ciudad, String email) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.email = email;
        this.puntuacion = 0;

        this.jugadores = new HashMap<>();
        this.porteros = new Portero[2];

        this.activo = false;
    }

    public Equipo(String sCSV) {
        String[] lineas = sCSV.split("\n");
        String[] columnas = lineas[0].split(":");
        if (columnas[0].equals("EQUIPO")) {
            String[] datos = columnas[1].split(";");
            this.nombre = datos[0];
            this.puntuacion = Integer.parseInt(datos[1]);
            this.ciudad = datos[2];
            this.email = datos[3];
        } else {
            return;
        }

        //Inicializo el Map de jugadores
        this.jugadores = new HashMap<>();

        String[] posiblesJugadores = sCSV.split("JUGADOR");
        String jugadorCSV;

        for (int i = 1; i < posiblesJugadores.length; i++) {
            jugadorCSV = "JUGADOR" + posiblesJugadores[i];
            Jugador j = new Jugador(jugadorCSV);
            jugadores.put(j.getDni(), j);
        }

        this.porteros = new Portero[2];
        //En caso de haber 2 porteros su longitud es 3
        String[] posiblesPorteros = sCSV.split("PORTERO");
        String porteroCSV;
        //Empieza desde 1 para empezar desde los datos.
        for (int i = 1; i < posiblesPorteros.length; i++) {
            porteroCSV = "PORTERO" + posiblesPorteros[i];
            Portero p = new Portero(porteroCSV);
            porteros[i - 1] = p;
        }
    }

    public boolean addPortero(Portero p) {
        //Si el primer portero está vacío...
        if (porteros[0] == null) {
            //Si el segundo portero existe...
            if (porteros[1] != null) {
                //Comprueba que los dni son diferentes...
                if (porteros[1].getDni().equals(p.getDni())) {
                    //Como no lo son, no lo añade y devuelve false.
                    return false;
                }
            }
            //Como los dni son diferentes o no existe el segundo jugador, lo añado y devuelvo true.
            porteros[0] = p;
            return true;
        } //Si el segundo portero está vacío (ya sabemos que el primero no)
        else if (porteros[1] == null) {
            //Compruebo si los dni son iguales...
            if (porteros[0].getDni().equals(p.getDni())) {
                //Lo son, entonces no lo añado y devuelvo false
                return false;
            }
            //Los dni son diferentes, entonces lo añado y devuelvo true.
            porteros[1] = p;
            return true;
        }
        //Los huecos para porteros están ambos ocupados.
        return false;
    }

    public boolean addJugador(Jugador j) {
        Jugador antiguo = jugadores.put(j.getDni(), j);
        //Si antiguo tiene algun valor, significa que ya había un jugador con ese código...
        if (antiguo != null) {
            //Vuelvo ha añadir el jugador que había y devuelvo false.
            jugadores.put(antiguo.getDni(), antiguo);
            return false;
        }
        //Si antiguo es null, el jugador se ha añadido correctamente.
        return true;
    }

    public boolean eliminaPortero(String dni) {
        //Si existe el primer portero...
        if (porteros[0] != null) {
            //Si tiene el dni pasado...
            if (porteros[0].getDni().equals(dni)) {
                //Lo elimino
                porteros[0] = null;
                return true;
            }
        }
        //Si existe el segundo portero...
        if (porteros[1] != null) {
            //Si tiene el dni pasado...
            if (porteros[1].getDni().equals(dni)) {
                //Lo elimino
                porteros[1] = null;
                return true;
            }
        }
        //Si llega aquí, significa que no existe ningún portero o ningún portero con el dni pasado por parámetro.
        return false;
    }
    
    public boolean eliminaJugador(String dni){
        //Pido un jugador del mapa con este dni como key. Si hay alguno...
        if(jugadores.get(dni) != null){
            //lo elimino y devuelvo true.
            jugadores.remove(dni);
            return true;
        }
        //Si no hay ninguno con este dni, devuelvo false.
        return false;
    }
    
    public boolean isActivo(){
        //Si hay al menos un portero y 5 jugadores, está activo.
        if((porteros[0] != null || porteros[1] != null) && jugadores.size() > 4){
            this.setActivo(true);
            return true;
        }
        this.setActivo(false);
        return false;
    }
    
    public ArrayList<Jugador> menoresEdad(){
        ArrayList<Jugador> menores = new ArrayList<>();
        for (Map.Entry<String, Jugador> entry : jugadores.entrySet()) {
            //Esto es un forEach para HashMap
            Jugador j = entry.getValue();
            if(!j.mayorEdad()){
                menores.add(j);
            }            
        }
        for(Portero p : porteros){
            if(p != null){
                if(!p.mayorEdad()){
                    menores.add(p);
                }
            }
        }
        return menores;
    }
    
    public ArrayList<Jugador> jugadoresTitulares(){
        ArrayList<Jugador> titulares = new ArrayList<>();
        //Si no está activo, devuelvo null
        if(!this.isActivo()){
            return null;
        }
        //Aquí, ya tengo asegurado que hay mínimo 1 portero y 5 jugadores
        
        //Si existen los dos porteros...
        if(porteros[0] != null && porteros[1] != null){
            //Si el primer portero ha encajado menos goles que el segundo...
            if(porteros[0].getGolesEncajados() <= porteros[1].getGolesEncajados()){
                //lo añado
                titulares.add(porteros[0]);
            }//Si no, añado al otro
            else{
                titulares.add(porteros[1]);
            }            
        }//Si existe el primer portero...
        else if(porteros[0] != null){
            titulares.add(porteros[0]);
        }//Si existe el segundo portero...
        else{
            titulares.add(porteros[1]);
        }
        //PORTEROS AÑADIDOS
        
        //Creo una lista con los jugadores para ordenarla
        ArrayList<Jugador> listaJugadores = new ArrayList<>(jugadores.values());
        //Ordeno por goles
        Collections.sort(listaJugadores, new ComparatorGoles());
        //Con esto recorro los 5 primeros jugadores de la lista ordenada
        for(int i=0;i<5;i++){
            //Voy añadiendo a los titulares a los 5 primeros jugadores de la lista ordenada
            titulares.add(listaJugadores.get(i));
        }
        //JUGADORES AÑADIDOS
        //Devuelvo la lista de titulares con 1 portero y 5 jugadores
        return titulares;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Map<String, Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Portero[] getPorteros() {
        return porteros;
    }

    public void setPorteros(Portero[] porteros) {
        this.porteros = porteros;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
    
    @Override
    public String toString() {
        String cadena;
        cadena = String.format("EQUIPO:%s;%d;%s;%s\n", nombre, puntuacion, ciudad, email);
        for (Portero portero : porteros) {
            if (portero != null) {
                cadena += portero.toString();
            }
        }

        for (Map.Entry<String, Jugador> entry : jugadores.entrySet()) {
            //Esto es un forEach para HashMap
            //Paso a CSV este objeto.
            cadena += entry.getValue().toString();
        }
        return cadena;
    }

}
