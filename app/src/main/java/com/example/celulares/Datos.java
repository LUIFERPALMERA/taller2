package com.example.celulares;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    private static  String db = "Celulares";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public static ArrayList<Celular> celulares = new ArrayList<>();

    public static void agregar(Celular p){
        databaseReference.child(db).child(p.getId()).setValue(p);
    }

    public static void editar(Celular p){
        databaseReference.child(db).child(p.getId()).setValue(p);
    }

    public static void eliminar(Celular p){
        databaseReference.child(db).child(p.getId()).removeValue();
    }

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void setPersonas(ArrayList<Celular> personas){
        Datos.celulares = personas;
    }

    public static ArrayList<Celular> obtener(){
        return Datos.celulares;
    }
}
