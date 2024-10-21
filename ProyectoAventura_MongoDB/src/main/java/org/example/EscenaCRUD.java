package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Clase con los métodos para gestionar las escenas
 */
public class EscenaCRUD {

//    Documento a escena

    /**
     * Documento a escena
     * @param doc documento para convertir
     * @return escena
     */
    public static Escena docAEscena(Document doc){

        if(doc==null) return null;

        Gson gson = new GsonBuilder().create();
        Escena escena = gson.fromJson(doc.toJson(), Escena.class);
        return escena;
    }

//    Consultar escena tomando ID como campo único

    /**
     * Consultar escena
     * @param coleccionE - colección de Mongo con las escenas
     * @param id ID de la escena
     * @return escena consultada
     */
    public static Escena consultarEscena(MongoCollection<Document> coleccionE, int id) {

        Document doc = coleccionE.find(eq("id",id)).first();
        return docAEscena(doc);
    }


}