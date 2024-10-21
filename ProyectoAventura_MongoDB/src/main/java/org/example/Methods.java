package org.example;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Clase con métodos generales del juego
 */
public class Methods {

//    Mostrar la vida de la Comunidad

    /**
     * Muestra la vida de la Comunidad
     * @param juego partida que se está llevando a cabo
     * @throws InterruptedException
     */
    public static void showVida(Juego juego) throws InterruptedException {
        System.out.println("\n·································\n" +
                "Salud de la Comunidad: " + juego.getVida() + " puntos.\n" +
                "·································\n");
    }

//    Guardar una partida en la colección records con las escenas que se han completado y los puntos de vida restantes al terminar

    /**
     * Guardar una partida en la colección records
     * @param juego partida que se está llevando a cabo
     * @param coleccionR - colección de Mongo con los records
     */
    public static void guardarRecord(Juego juego, MongoCollection<Document> coleccionR) {
        System.out.print("FIN DEL JUEGO\nPuntuación: " + juego.getTotalEscenas() + " escenas y " + juego.getVida() + " puntos de vida restantes.\n\n");
        JuegoCRUD.insertarRecord(coleccionR, juego);
    }

//    Sacar opciones disponibles

    /**
     * Sacar opciones disponibles
     * @param juego partida que se está llevando a cabo
     * @param escenaConsultada escena con las opciones
     * @return ArrayList de opciones
     */
    public static ArrayList <Opcion> sacarOpciones(Juego juego, Escena escenaConsultada) {

        ArrayList<Opcion> opcionesJugador = new ArrayList<Opcion>();

        for (Opcion opcion : escenaConsultada.getOpciones()) {
            opcionesJugador.add(opcion);
        }

        return opcionesJugador;
    }

//    Setear las opciones y añadir la de ver los puntos de vida siempre que la escena actual no sea de derrota o victoria

    /**
     * Setear las opciones y añadir la de ver los puntos de vida
     * @param juego partida que se está llevando a cabo
     * @param escenaConsultada escena con las opciones
     * @return ArrayList de opciones
     */
    public static ArrayList <Opcion> opcionesJ(Juego juego, Escena escenaConsultada) {
        ArrayList <Opcion> opciones = sacarOpciones(juego, escenaConsultada);
        if (escenaConsultada.getId() != 4 &&
                escenaConsultada.getId() != 5 &&
                escenaConsultada.getId() != 7 &&
                escenaConsultada.getId() != 9 &&
                escenaConsultada.getId() != 10 &&
                escenaConsultada.getId() != 14 &&
                escenaConsultada.getId() != 18 &&
                escenaConsultada.getId() != 19 &&
                escenaConsultada.getId() != 21 &&
                escenaConsultada.getId() != 23 &&
                escenaConsultada.getId() != 24 &&
                escenaConsultada.getId() != 25) {
            opciones.add(new Opcion(opciones.size() + 1, "Ver salud de la Comunidad", escenaConsultada.getId()));
        }
        return opciones;
    }

//    Mostrar las opciones con su número y texto

    /**
     * Mostrar las opciones con su número y texto
     * @param opciones listado de opciones
     * @return opción seleccionada por el jugador
     */
    public static int showOpciones(ArrayList <Opcion> opciones) {

        int op = 1;
        for (Opcion opcion : opciones) {
            System.out.println(op + " - " + opcion.getTextoOp());
            op++;
        }
        return AppMethods.numInput(op-1);
    }
}