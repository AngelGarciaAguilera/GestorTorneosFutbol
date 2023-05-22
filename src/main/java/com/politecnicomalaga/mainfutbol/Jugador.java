/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainfutbol;

import java.time.YearMonth;

/**
 *
 * @author mint
 */
public class Jugador {

    protected String nombre;
    protected String apellidos;
    protected String dni;
    protected String email;
    protected String telefono;
    protected int nacimiento;
    protected int dorsal;
    protected int goles;

    public Jugador(String nombre, String apellidos, String dni, String email, String telefono, int nacimiento, int dorsal, int goles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        //Si el año de nacimiento que nos pasan es menor de 1950 o mayor del año actual...
        if (nacimiento < 1950 || nacimiento > YearMonth.now().getYear()) {
            //Asignamos por defecto que es de 2023 (último año posible).
            this.nacimiento = 2023;
        } else {
            this.nacimiento = nacimiento;
        }
        //Si el dorsal que nos pasan es menor de 1 o mayor de 99...
        if (dorsal < 1 || dorsal > 99) {
            //Asignamos por defecto que es de 100 (no tiene dorsal).
            this.dorsal = 100;
        } else {
            this.dorsal = dorsal;
        }
        //Si nos pasan un número negativo de goles...
        if (goles < 0) {
            this.goles = 0;
        } else {
            this.goles = goles;
        }
    }

    public Jugador(String sCSV) {
        String[] lineas = sCSV.split("\n");
        String[] columnas = lineas[0].split(":");
        String[] datos = columnas[1].split(";");
        
        this.nombre = datos[0];
        this.apellidos = datos[1];
        this.dni = datos[2];
        this.email = datos[3];
        this.telefono = datos[4];
        this.nacimiento = Integer.parseInt(datos[5]);
        this.dorsal = Integer.parseInt(datos[6]);
        this.goles = Integer.parseInt(datos[7]);

    }

    public boolean mayorEdad() {
        int edad = YearMonth.now().getYear() - nacimiento;

        return edad >= 18;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        if (nacimiento < 1950 || nacimiento > YearMonth.now().getYear()) {
            //Asignamos por defecto que es de 2023 (último año posible).
            this.nacimiento = 2023;
        } else {
            this.nacimiento = nacimiento;
        }
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        if (dorsal < 1 || dorsal > 99) {
            //Asignamos por defecto que es de 100 (no tiene dorsal).
            this.dorsal = 100;
        } else {
            this.dorsal = dorsal;
        }
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        if (goles >= 0) {
            this.goles += goles;
        }
    }

    @Override
    public String toString() {
        return String.format("JUGADOR:%s;%s;%s;%s;%s;%d;%d;%d\n", nombre, apellidos, dni, email, telefono, nacimiento, dorsal, goles);
    }

}
