/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.politecnicomalaga.mainfutbol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mint
 */
public class MainFutbol {

    private static Torneo miTorneo;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        int opcion;

        do {
            //mostrar menú
            menuPrincipal();
            opcion = leerIntTeclado(sc);
            //Si se escoge una opcion diferente a generarOficina y la Oficina no existe, avisa de que hay que generarla primero.
            if (opcion > 1 && opcion < 8 && MainFutbol.miTorneo == null) {
                System.out.println("El Torneo no ha sido creado.");
            } else {
                switch (opcion) {
                    case 1:
                        generarTorneo(sc);
                        break;

                    case 2:
                        generarEquipo(sc);
                        break;

                    case 3:
                        altaJugador(sc);
                        break;

                    case 4:
                        eliminarJugador(sc);
                        break;

                    case 5:
                        altaPortero(sc);
                        break;

                    case 6:
                        eliminarPortero(sc);
                        break;

                    case 7:
                        mostrarJugadoresMenores(sc);
                        break;

                    case 8:
                        mostrarJugadoresTitulares(sc);
                        break;

                    case 9:
                        mostrarCapitanes();
                        break;

                    case 10:
                        saveEquipo();
                        break;

                    case 11:
                        loadOficina();
                        break;

                    case -1:
                        System.out.println("Introduzca un número como opción.");
                        break;

                    default:
                        continuar = false;
                        sc.close();
                        break;
                }
            }
        } while (continuar);
    }

    private static void menuPrincipal() {
        System.out.println("---------------------------------------");
        System.out.println("MENÚ");
        System.out.println("\n1. Crear/modificar Torneo.");
        System.out.println("2. Añadir Equipo.");
        System.out.println("3. Añadir Jugador.");
        System.out.println("4. Eliminar Jugador.");
        System.out.println("5. Añadir Portero.");
        System.out.println("6. Eliminar Portero.");
        System.out.println("7. Listar Jugadores menores de edad.");
        System.out.println("8. Listar titulares.");
        System.out.println("9. Listar capitanes.");
        System.out.println("10. Guardar datos Torneo CSV.");
        System.out.println("11. Cargar datos Torneo CSV.");
        System.out.println("Otra opción: SALIR");
        System.out.println("---------------------------------------");
        System.out.println("\nSeleccione una opción:");
    }

    // Recogemos un número de teclado, si nos dan algo que no es un número, ponemos
    // -1 para repetir entrada
    private static int leerIntTeclado(Scanner sc) {
        int iOpcion;
        try {
            iOpcion = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
        return iOpcion;
    }

    // Recogemos un número float de teclado, si nos dan algo que no es un número
    // float, ponemos -1f
    private static float leerFloatTeclado(Scanner sc) {
        float fOpcion;
        try {
            fOpcion = sc.nextFloat();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1f;
        }
        return fOpcion;
    }

    // Recoger un string de teclado
    private static String leerStringTeclado(Scanner sc) {

        String sEntrada;
        try {
            sEntrada = sc.nextLine();
        } catch (InputMismatchException e) {
            return "";
        }
        return sEntrada;
    }

    private static void generarTorneo(Scanner sc) {
        String nombre, ciudad, fecha;

        System.out.println("\n---------------------------------------");
        System.out.println("GENERAR TORNEO");
        System.out.println("---------------------------------------");
        //Pido los datos y los almaceno.
        System.out.println("Nombre del Torneo:");
        nombre = leerStringTeclado(sc);
        System.out.println("Ciudad del Torneo:");
        ciudad = leerStringTeclado(sc);
        System.out.println("Fecha del Torneo:");
        fecha = leerStringTeclado(sc);
        System.out.println("---------------------------------------");

        if (miTorneo == null) {
            miTorneo = new Torneo(nombre, ciudad, fecha);
            System.out.println("Torneo generado con éxito.");
        } else {
            miTorneo.setNombre(nombre);
            miTorneo.setCiudad(ciudad);
            miTorneo.setFecha(fecha);
            System.out.println("Los datos del Torneo se han actualizado con éxito.");
        }
    }

    private static void generarEquipo(Scanner sc) {
        String nombre, ciudad, email;

        System.out.println("\n---------------------------------------");
        System.out.println("GENERAR EQUIPO");
        System.out.println("---------------------------------------");
        //Pido los datos y los almaceno.
        System.out.println("Nombre del Equipo:");
        nombre = leerStringTeclado(sc);
        System.out.println("Ciudad del Equipo:");
        ciudad = leerStringTeclado(sc);
        System.out.println("Email del Equipo:");
        email = leerStringTeclado(sc);
        System.out.println("---------------------------------------");

        Equipo equipo = new Equipo(nombre, ciudad, email);
        if (miTorneo.addEquipo(equipo)) {
            System.out.println("Equipo generada con éxito.");
        } else {
            System.out.println("El Equipo no se ha podido añadir.");
        }
    }

    private static void altaJugador(Scanner sc) {
        String equipo, dni, nombre, apellidos, telefono, email;
        int yNacimiento, goles, dorsal;

        System.out.println("\n---------------------------------------");
        System.out.println("ALTA JUGADOR");
        System.out.println("---------------------------------------");
        System.out.println("Introduzca el nombre del Equipo al que pertenece:");
        equipo = leerStringTeclado(sc);
        if (miTorneo.getEquipo(equipo) == null) {
            System.out.println("El equipo introducido no existe.");
        } else {
            //Pido los datos y los almaceno.
            System.out.println("Nombre:");
            nombre = leerStringTeclado(sc);
            System.out.println("Apellidos:");
            apellidos = leerStringTeclado(sc);
            System.out.println("DNI:");
            dni = leerStringTeclado(sc);
            System.out.println("Email:");
            email = leerStringTeclado(sc);
            System.out.println("Teléfono:");
            telefono = leerStringTeclado(sc);
            System.out.println("Año nacimiento:");
            yNacimiento = leerIntTeclado(sc);
            System.out.println("Dorsal:");
            dorsal = leerIntTeclado(sc);
            System.out.println("Goles:");
            goles = leerIntTeclado(sc);

            System.out.println("---------------------------------------");

            Jugador j = new Jugador(nombre, apellidos, dni, email, telefono, yNacimiento, dorsal, goles);

            if (miTorneo.getEquipo(equipo).addJugador(j)) {
                System.out.println("Jugador dado de alta con éxito.");
            } else {
                System.out.println("El Jugador no se ha podido dar de alta.");
            }
        }

    }

    private static void altaPortero(Scanner sc) {
        String equipo, dni, nombre, apellidos, telefono, email;
        int yNacimiento, goles, dorsal, golesEncajados;

        System.out.println("\n---------------------------------------");
        System.out.println("ALTA PORTERO");
        System.out.println("---------------------------------------");
        System.out.println("Introduzca el nombre del Equipo al que pertenece:");
        equipo = leerStringTeclado(sc);
        if (miTorneo.getEquipo(equipo) == null) {
            System.out.println("El equipo introducido no existe.");
        } else {
            //Pido los datos y los almaceno.
            System.out.println("Nombre:");
            nombre = leerStringTeclado(sc);
            System.out.println("Apellidos:");
            apellidos = leerStringTeclado(sc);
            System.out.println("DNI:");
            dni = leerStringTeclado(sc);
            System.out.println("Email:");
            email = leerStringTeclado(sc);
            System.out.println("Teléfono:");
            telefono = leerStringTeclado(sc);
            System.out.println("Año nacimiento:");
            yNacimiento = leerIntTeclado(sc);
            System.out.println("Dorsal:");
            dorsal = leerIntTeclado(sc);
            System.out.println("Goles:");
            goles = leerIntTeclado(sc);
            System.out.println("Goles encajados:");
            golesEncajados = leerIntTeclado(sc);
            System.out.println("---------------------------------------");

            Portero p = new Portero(nombre, apellidos, dni, email, telefono, yNacimiento, dorsal, goles, golesEncajados);
            if (miTorneo.getEquipo(equipo).addPortero(p)) {
                System.out.println("Portero dado de alta con éxito.");
            } else {
                System.out.println("El Portero no se ha podido dar de alta.");
            }
        }
    }

    private static void eliminarJugador(Scanner sc) {
        String equipo, dni;

        System.out.println("\n---------------------------------------");
        System.out.println("ELIMINAR JUGADOR");
        System.out.println("---------------------------------------");
        System.out.println("Introduzca el nombre del Equipo al que pertenece:");
        equipo = leerStringTeclado(sc);
        if (miTorneo.getEquipo(equipo) == null) {
            System.out.println("El equipo introducido no existe.");
        } else {
            System.out.println("DNI:");
            dni = leerStringTeclado(sc);

            if (miTorneo.getEquipo(equipo).eliminaJugador(dni)) {
                System.out.println("Jugador eliminado con éxito.");
            } else {
                System.out.println("El Jugador no se ha podido eliminar.");
                System.out.println("Compruebe que el DNI introducido es correcto.");
            }
        }
    }

    private static void eliminarPortero(Scanner sc) {
        String equipo, dni;

        System.out.println("\n---------------------------------------");
        System.out.println("ELIMINAR PORTERO");
        System.out.println("---------------------------------------");
        System.out.println("Introduzca el nombre del Equipo al que pertenece:");
        equipo = leerStringTeclado(sc);
        if (miTorneo.getEquipo(equipo) == null) {
            System.out.println("El equipo introducido no existe.");
        } else {
            System.out.println("DNI:");
            dni = leerStringTeclado(sc);

            if (miTorneo.getEquipo(equipo).eliminaPortero(dni)) {
                System.out.println("Portero eliminado con éxito.");
            } else {
                System.out.println("El Portero no se ha podido eliminar.");
                System.out.println("Compruebe que el DNI introducido es correcto.");
            }
        }
    }

    private static void mostrarJugadoresMenores(Scanner sc) {
        String equipo;

        System.out.println("\n---------------------------------------");
        System.out.println("MOSTRAR MENORES");
        System.out.println("---------------------------------------");

        System.out.println("Introduzca el nombre del Equipo al que pertenece:");
        equipo = leerStringTeclado(sc);
        if (miTorneo.getEquipo(equipo) == null) {
            System.out.println("El equipo introducido no existe.");
        } else {
            if (!miTorneo.getEquipo(equipo).menoresEdad().isEmpty()) {
                ArrayList<Jugador> j = miTorneo.getEquipo(equipo).menoresEdad();
                for (int i = 0; i < j.size(); i++) {
                    Jugador jg = j.get(i);
                    System.out.println(jg.toString());
                }
            } else {
                System.out.println("Aún no se ha añadido ningún jugador menor de edad.");
            }
        }
    }

    private static void mostrarJugadoresTitulares(Scanner sc) {
        String equipo;

        System.out.println("\n---------------------------------------");
        System.out.println("MOSTRAR JUGADORES TITULARES");
        System.out.println("---------------------------------------");

        System.out.println("Introduzca el nombre del Equipo al que pertenece:");
        equipo = leerStringTeclado(sc);
        if (miTorneo.getEquipo(equipo) == null) {
            System.out.println("El equipo introducido no existe.");
        } else {
            if (miTorneo.getEquipo(equipo).jugadoresTitulares() != null) {
                ArrayList<Jugador> j = miTorneo.getEquipo(equipo).jugadoresTitulares();
                for (int i = 0; i < j.size(); i++) {
                    Jugador jg = j.get(i);
                    System.out.println(jg.toString());
                }
            } else {
                System.out.println("El equipo no cuenta con suficientes jugadores o porteros para hacer una alineación titular.");
            }
        }
    }

    private static void mostrarCapitanes() {
        if (miTorneo.mostrarCapitanes() != null) {
            ArrayList<Jugador> j = miTorneo.mostrarCapitanes();
            for (int i = 0; i < j.size(); i++) {
                Jugador jg = j.get(i);
                System.out.println(jg.toString());
            }
        } else {
            System.out.println("El torneo no cuenta con suficientes equipos para mostrar los capitanes.");
        }
    }

    private static void saveEquipo() {
        try {
            if (ControladorFicheros.grabarTorneoCSV(miTorneo, "Torneo.csv")) {
                System.out.println("Se ha exportado el equipo...");
            } else {
                System.out.println("Algo ha ido mal.");
            }
        } catch (IOException io) {
            System.out.println("No se ha encontrado el archivo.");
        }
    }

    private static void loadOficina() {
        miTorneo = ControladorFicheros.leerTorneoCSV("Torneo.csv");
        System.out.println("Fichero cargado con éxito.");
    }
}
