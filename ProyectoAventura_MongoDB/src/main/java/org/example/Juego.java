package org.example;

/**
 * Clase para gestionar las partidas guardadas o records
 */
public class Juego {
//    ID de la partida
    private int id;
//    Nombre del jugador
    private String nombreJugador;
//    Puntos de vida de la Comunidad
    private int vida;
//    Escenas completadas
    private int totalEscenas;

    public Juego() {
    }

    public Juego(int id, String nombreJugador, int vida, int totalEscenas) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.vida = vida;
        this.totalEscenas = totalEscenas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getTotalEscenas() {
        return totalEscenas;
    }

    public void setTotalEscenas(int totalEscenas) {
        this.totalEscenas = totalEscenas;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "id=" + id +
                ", nombreJugador='" + nombreJugador + '\'' +
                ", vida=" + vida +
                ", totalEscenas=" + totalEscenas +
                '}';
    }
}