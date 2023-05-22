/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainfutbol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author mint
 */
class ControladorFicheros {

    public static boolean writeText(String ruta, String texto) {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileWriter(ruta));
            pw.print(texto);
            pw.flush();
        } catch (IOException io) {
            io.getMessage();
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return true;
    }

    public static String readText(String ruta) {
        Scanner sc = null;
        String texto = "";

        try {
            sc = new Scanner(new FileReader(ruta));
            while (sc.hasNext()) {
                texto += sc.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return texto;
    }

    public static boolean grabarTorneoCSV(Torneo miTorneo, String sNombreFichero) throws IOException{
        return writeText(sNombreFichero, miTorneo.toString());
    }

    public static Torneo leerTorneoCSV(String sNombreFichero) {
        return new Torneo(readText(sNombreFichero));
    }
}
