package com.example.lalo.sendmessages.Models;


public class Productos  {

    private String Nombre;
    private String Precio;
    private String Imagen;

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
}
