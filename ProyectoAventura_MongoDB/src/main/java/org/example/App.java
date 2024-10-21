package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.example.AppMethods.*;
import static org.example.Methods.*;

/**
 * Clase del método main
 */
public class App {

    /**
     * Método main
     * @param args
     */
    public static void main(String[] args) {

        try {
//        Título
            System.out.println("\n" +
                    "  ___  __ __  ____ __  __ ______ __ __ ____   ___      ____ __  __    __     ___     ______ __  ____ ____  ____   ___     ___  ___  ____ ____   __  ___ \n" +
                    " // \\\\ || || ||    ||\\ || | || | || || || \\\\ // \\\\    ||    ||\\ ||    ||    // \\\\    | || | || ||    || \\\\ || \\\\ // \\\\    ||\\\\//|| ||    || \\\\  || // \\\\\n" +
                    " ||=|| \\\\ // ||==  ||\\\\||   ||   || || ||_// ||=||    ||==  ||\\\\||    ||    ||=||      ||   || ||==  ||_// ||_// ||=||    || \\/ || ||==  ||  )) || ||=||\n" +
                    " || ||  \\V/  ||___ || \\||   ||   \\\\_// || \\\\ || ||    ||___ || \\||    ||__| || ||      ||   || ||___ || \\\\ || \\\\ || ||    ||    || ||___ ||_//  || || ||\n" +
                    "                                                                                                                                                        \n");

//        Quitar mensaje de warning
            Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
//        MongoClient
            MongoClient cliente = MongoClients.create("mongodb://localhost:27017/");

//        Variables
            int opcionJ;
            int escenaSiguiente;
            boolean fin = false;
            boolean escena = true;
            boolean guardar = false;
            boolean validarOp = false;
            String validar;
            int i = 0;
            Escena escenaConsultada;
//                ArrayList coleccion para leer y guardar aquí todas las escenas al ejecutar el código
            ArrayList<Escena> coleccion = new ArrayList<Escena>();


            try {

                do {
//                Permitir entrada de texto mediante Scanner
                    Scanner teclado = new Scanner(System.in);

//                Definir la base de datos y las colecciones en las que gestionar la informacion
                    MongoDatabase conexion = cliente.getDatabase("Aventura");
                    MongoCollection<Document> coleccionE = conexion.getCollection("escenas");
                    MongoCollection<Document> coleccionR = conexion.getCollection("records");

//                Reiniciar variables
                    opcionJ = 0;
                    escenaSiguiente = 1;
                    fin = false;
                    escena = true;
                    guardar = false;
                    validar = "";
                    escenaConsultada = new Escena();
                    coleccion = new ArrayList<Escena>();

//                Leer las escenas y guardarlas en el array coleccion
                    do {
                        i++;
                        escenaConsultada = EscenaCRUD.consultarEscena(coleccionE, i);
                        coleccion.add(escenaConsultada);

                    } while (i < coleccionE.countDocuments());

//                Ejecutar menú
                    if (menu(coleccionR)) {

//                    Pedir nombre al jugador
                        System.out.print("Introduce tu nombre: ");

//                    Objeto Juego:
//                    numero de la partida que se está jugando (nª partidas previas + 1),
//                    nombre,
//                    puntos de vida iniciales,
//                    total de escenas que ha completado (iniciar en 0)
                        Juego juego = new Juego(JuegoCRUD.listarRecords(coleccionR).size() + 1, nombreJ(), 20, 0);

//                    Ejecutar juego
//                    Consultar escena y setea1,r vida según los puntos que se pierdan o ganen en cada escena
                        do {
                                escenaConsultada = EscenaCRUD.consultarEscena(coleccionE, escenaSiguiente);
                                juego.setVida(juego.getVida() + escenaConsultada.getPuntosVida());
                                System.out.println("");

//                        Si hay un cambio en los puntos de vida, se imprimirá por pantalla
                            if (escenaConsultada.getPuntosVida() > 0) {
                                System.out.println("Puntos de vida => +" + escenaConsultada.getPuntosVida());
                            } else if (escenaConsultada.getPuntosVida() < 0) {
                                System.out.println("Puntos de vida => " + escenaConsultada.getPuntosVida());
                            } else {

                            }

//                        Imprimir texto de escena
                            System.out.println(escenaConsultada.getTexto());

//                        Cuando la vida sea inferior a 10 (caso de derrota) o la escena actual sea la 25 (única escena de victoria), el juego acabará y se guardará el record
                            if (juego.getVida() < 10 || escenaConsultada.getId() == 25) {
                                guardar = true;
                                break;

                            } else {

                                do {
//                                El jugador elige una opción
                                    ArrayList<Opcion> opcionesJugador = opcionesJ(juego, escenaConsultada);
                                    opcionJ = showOpciones(opcionesJugador);

                                    escenaSiguiente = opcionesJugador.get(opcionJ - 1).getEscena();
                                    validar = opcionesJugador.get(opcionJ - 1).getTextoOp();

//                      Añadir opción para ver la salud actual de la Comunidad (dispoible siempre excepto en las escenas de muerte y victoria)
                                    if (validar.equals("Ver salud de la Comunidad")) {
                                        showVida(juego);
                                        escena = false;
                                        System.out.println(escenaConsultada.getTexto());
                                    } else {
                                        juego.setTotalEscenas(juego.getTotalEscenas() + 1);
                                        escena = true;
                                    }
                                } while (!escena);
                            }

//                        El bucle acabará cuando se llegue a una escena de derrota o victoria
                        } while (escenaConsultada.getId() != 4 ||
                                escenaConsultada.getId() != 5 ||
                                escenaConsultada.getId() != 7 ||
                                escenaConsultada.getId() != 9 ||
                                escenaConsultada.getId() != 10 ||
                                escenaConsultada.getId() != 14 ||
                                escenaConsultada.getId() != 18 ||
                                escenaConsultada.getId() != 19 ||
                                escenaConsultada.getId() != 21 ||
                                escenaConsultada.getId() != 23 ||
                                escenaConsultada.getId() != 24 ||
                                escenaConsultada.getId() != 25);

//                    Guardar record si la variable guardar es true
                        if (guardar) {
                            Methods.guardarRecord(juego, coleccionR);
                        }

//                    Salir del juego
                        if (validar.equals("Salir")) {
                            fin = true;
                        }

                    } else {
                        fin = true;
                    }
                } while (!fin);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);

//            Cerrar cliente en el finally
            } finally {
                if (cliente != null) {
                    cliente.close();
                }
            }
//            Excepción controlada si no se encuentra la colección en Mongo
        }catch (NullPointerException npe) {
            System.out.println("\nERROR: La colección no existe o está vacía.");
        }
    }
}