/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainfutbol;

/**
 *
 * @author mint
 */
public class Portero extends Jugador {
    
    private int golesEncajados;

    public Portero(String nombre, String apellidos, String dni, String email, String telefono, int nacimiento, int dorsal, int goles, int golesEncajados) {
        super(nombre, apellidos, dni, email, telefono, nacimiento, dorsal, goles);
        if(golesEncajados < 0){
            this.golesEncajados = 0;
        }else{
            this.golesEncajados = golesEncajados;
        }        
    }
    
    
    
    public Portero(String sCSV){
        super(sCSV);
        
        String[] lineas = sCSV.split("\n");
        String[] columnas = lineas[0].split(":");
        if("PORTERO".equals(columnas[0])){
            String[] datos = columnas[1].split(";");
            this.golesEncajados = Integer.parseInt(datos[8]);
        }
    }

    public int getGolesEncajados() {
        return golesEncajados;
    }

    public void setGolesEncajados(int golesEncajados) {
        if(golesEncajados >= 0){
            this.golesEncajados += golesEncajados;
        }        
    }
    
    @Override
    public String toString(){
        return String.format("PORTERO:%s;%s;%s;%s;%s;%d;%d;%d;%d\n", nombre, apellidos, dni, email, telefono, nacimiento, dorsal, goles, golesEncajados);
    }
    
    
    
    
}
