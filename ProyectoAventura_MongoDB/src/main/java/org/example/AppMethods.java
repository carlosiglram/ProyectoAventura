package org.example;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import static com.mongodb.client.model.Sorts.ascending;

import java.util.*;

/**
 * Clase con los métodos para utilizar en la clase App
 */
public class AppMethods {

//    Método para introducir número
//    Controlar que sea un número y esté dentro del rango de opciones disponibles

    /**
     * Permite introducir un número
     * @param max = límite numérico para las elecciones
     * @return número introducido por el jugador
     */
    public static int numInput(int max) {
        int numero = 0;
        boolean validar = false;

        do {
            System.out.print("\nTu elección: ");
            Scanner scanner = new Scanner(System.in);

            try {

                do {
                    numero = scanner.nextInt();
                    if (numero > max || numero <= 0) {
                        System.out.print("Introduce el número asignado a una de las opciones.\nTu elección: ");
                    }
                } while (numero > max || numero <= 0);
                validar = true;

            } catch (Exception e) {
                System.out.print("Introduce el número asignado a una de las opciones.");
                validar = false;
            }
        } while (!validar);
        return numero;
    }

//    Método para introducir nombre del jugador
//    Controlar que el campo no esté vacío

    /**
     * Pide el nombre
     * @return nombre introducido por el jugador
     */
    public static String nombreJ() {
        String nombre;

        do {
            Scanner teclado = new Scanner(System.in);
            nombre = teclado.nextLine();

            if (nombre.isEmpty()) {
                System.out.print("Introduce un nombre: ");
            }
        } while (nombre.isEmpty());

        return nombre;
    }

//    Mostrar introducción

    /**
     * Muestra el texto de introducción
     */
    private static void showIntro() {
        System.out.println("\n--- INTRODUCCIÓN ---\n\n" +
                "Has llegado a Rivendel, refugio de los elfos en la Tierra Media. Un lugar hermoso donde reina la paz pero si estás allí es porque se te ha asignado un duro e importante cometido.\n" +
                "Elrond, señor de Rivendel, te ha encomendado a ti la tarea de llevar el Anillo Único al Monte del Destino, en Mordor, y destruirlo.\n" +
                "Pero no irás solo, te acompañarán ocho personas: Gandalf, Aragorn, Boromir, Legolas, Gimli, Sam, Pippin y Merry.\n" +
                "El grupo de los nueve recibe el nombre de La Comunidad del Anillo.\n\n" +
                "La Comunidad tiene 20 puntos de salud repartidos entre los nueve miembros:\n" +
                "4 - Gandalf\n4 - Aragorn\n" +
                "3 - Boromir\n3 - Legolas\n" +
                "2 - Gimli\n" +
                "1 - Sam\n1 - Pippin\n1 - Merry\n1 - Tú\n\n" +
                "La salud de la Comunidad debe ser de 10 o más puntos para poder llegar a Mordor.\n" +
                "Si en algún momento baja de 10, la aventura terminará.\n");
    }

//    Mostrar records

    /**
     * Muestra los records
     * @param coleccionR - colección de Mongo con los records
     */
    private static void showRecords(MongoCollection<Document> coleccionR) {
        int posicion = 1;

        List<Juego> juegos = JuegoCRUD.listarRecords(coleccionR);

//        Ordenar records de manera descendente en función de las escenas completadas
        Collections.sort(juegos, new Comparator<Juego>() {
            public int compare(Juego j1, Juego j2) {
                if (j1.getTotalEscenas() == j2.getTotalEscenas())
                    return 0;
                return j1.getTotalEscenas() > j2.getTotalEscenas() ? -1 : 1;
            }
        });

        System.out.println("");
        System.out.println("--- RECORDS ---\n");
        for (Juego juego : juegos
        ) {
            do {
                System.out.print(posicion + ". " + juego.getNombreJugador() + " - " + juego.getTotalEscenas() + " escenas y " + juego.getVida() + " puntos de vida restantes.\n");
                posicion++;
            } while (posicion <= 1);
        }
        System.out.println("----------------------\n");
    }

//    Mostrar menú

    /**
     * Muestra el menú
     * @return opcion seleccionada por el jugador
     */
    public static int showMenu() {
        System.out.println("--- MENÚ ---\n\n" +
                "1 - Introducción\n" +
                "2 - Jugar\n" +
                "3 - Records\n" +
                "4 - Salir");

        return numInput(4);
    }

//    Permite elecciones en el menú

    /**
     * Permite elecciones en el menú
     * @param coleccionR -colección de Mongo con los records
     * @return true (jugar) / false (salir del juego)
     * @throws InterruptedException
     */
    public static boolean menu(MongoCollection<Document> coleccionR) throws InterruptedException {
        int numero = 0;

        do {
            numero = showMenu();

            if (numero == 1) {
                showIntro();

            } else if (numero == 2) {
                return true;

            } else if (numero == 3) {
                showRecords(coleccionR);

            } else {
                return false;
            }

        } while (numero == 1 || numero == 3);
        return false;
    }
}