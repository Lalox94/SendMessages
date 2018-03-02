package com.example.lalo.sendmessages.Models;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Productos  {

    private String Nombre;
    private String Precio;
    private String Imagen;
    private static int Total = 0;
    private static JSONObject obj = new JSONObject();
    private static String test;
    public Productos() {}

    public Productos(String Imagen, String Nombre, String Precio) {
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Imagen = Imagen;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public static void setPriceForProduct(String productName, int price) {
        try {
            obj.put(productName,price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static int getTest(){

        try {
            for(int i = 0; i<obj.names().length(); i++)
                Total += obj.getInt(obj.names().getString(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Total;
    }

    public static void resetTotalAmountProducts() {
        Total = 0;
    }


}
