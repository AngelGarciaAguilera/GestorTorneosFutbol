/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainfutbol;

import java.util.Comparator;

/**
 *
 * @author mint
 */
public class ComparatorGoles implements Comparator<Jugador>{

    @Override
    public int compare(Jugador o1, Jugador o2) {
        return o2.getGoles()-o1.getGoles();
    }

    
}
