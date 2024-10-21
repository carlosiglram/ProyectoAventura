package org.example;

import java.util.Arrays;

/**
 * Clase para gestionar las escenas
 */
public class Escena {

//    ID de la escena actual
    private int id;
//    Texto de la escena
    private String texto;
//    Puntos de vida de la Comunidad
    private int puntosVida;
//    Opciones que tiene la escena
    private Opcion[] opciones;

    public Escena() {
    }

    public Escena(int id, String texto, int puntosVida, Opcion[] opciones) {
        this.id = id;
        this.texto = texto;
        this.puntosVida = puntosVida;
        this.opciones = opciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public Opcion[] getOpciones() {
        return opciones;
    }

    public void setOpciones(Opcion[] opciones) {
        this.opciones = opciones;
    }

    @Override
    public String toString() {
        return "Escena{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", puntosVida=" + puntosVida +
                ", opciones=" + Arrays.toString(opciones) +
                '}';
    }
}