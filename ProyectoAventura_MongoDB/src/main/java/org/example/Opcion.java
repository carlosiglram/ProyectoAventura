package org.example;

/**
 *  Clase para gestionar las opciones
 */
public class Opcion {

//    Número de opción
    private int numOp;
//    Texto que se muestra en la opción
    private String textoOp;
//    ID de la escena a la que redirige la opción
    private int escena;

    public Opcion() {
    }

    public Opcion(int numOp, String textoOp, int escena) {
        this.numOp = numOp;
        this.textoOp = textoOp;
        this.escena = escena;
    }

    public int getNumOp() {
        return numOp;
    }

    public void setNumOp(int numOp) {
        this.numOp = numOp;
    }

    public String getTextoOp() {
        return textoOp;
    }

    public void setTextoOp(String textoOp) {
        this.textoOp = textoOp;
    }

    public int getEscena() {
        return escena;
    }

    public void setEscena(int escena) {
        this.escena = escena;
    }

    @Override
    public String toString() {
        return "Opcion{" +
                "numOp=" + numOp +
                ", textoOp='" + textoOp + '\'' +
                ", escena=" + escena +
                '}';
    }
}