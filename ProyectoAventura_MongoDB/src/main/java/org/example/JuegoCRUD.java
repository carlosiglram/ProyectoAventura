package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Clase con los métodos para gestionar los records
 */
public class JuegoCRUD {

//    Convertir Record a Documento

    /**
     * Convertir Record a Documento
     * @param juego para convertir
     * @return documento
     */
    public static Document recordADoc(Juego juego){

        if (juego == null) return null;

        Gson gson = new GsonBuilder().create();
        Document doc = Document.parse(gson.toJson(juego));

        return doc;
    }

//    Convertir Documento a Record

    /**
     * Convertir Documento a Record
     * @param doc a convertir
     * @return juego
     */
    public static Juego docARecord(Document doc){
        if(doc==null) return null;

        Gson gson = new GsonBuilder().create();
        Juego juego = gson.fromJson(doc.toJson(), Juego.class);

        return juego;
    }

//    Consultar record

    /**
     * Consultar record
     * @param coleccionE - colección de Mongo con las escenas
     * @param id ID del record
     * @return Record consultado
     */
    public static Juego consultarRecord(MongoCollection<Document> coleccionE, int id) {
        Document doc = coleccionE.find(eq("id",id)).first();
        return docARecord(doc);
    }

//    Listar los records almacenados

    /**
     * Listar los records almacenados
     * @param coleccionE - colección de Mongo con las escenas
     * @return listado de records
     */
    public static List<Juego> listarRecords(MongoCollection<Document> coleccionE) {
        List<Juego> juegos = new ArrayList<>();
        List<Document> documentos = coleccionE.find().into(new ArrayList<Document>());
        Juego juego;

        for(Document doc : documentos){
            juego = docARecord(doc);
            juegos.add(juego);
        }
        return juegos;
    }

//    Insertar un nuevo record

    /**
     * Insertar un nuevo record
     * @param coleccionE - colección de Mongo con las escenas
     * @param juego record para insertar
     */
    public static void insertarRecord(MongoCollection<Document> coleccionE, Juego juego) {
        Document doc = recordADoc(juego);

        try{
            if(consultarRecord(coleccionE, juego.getId())==null){
                coleccionE.insertOne(doc);
            }else{
                System.out.println("ERROR. No es posible insertar la partida.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}