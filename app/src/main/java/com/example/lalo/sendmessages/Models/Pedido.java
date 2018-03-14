package com.example.lalo.sendmessages.Models;



public class Pedido  {

    private String NombreProducto;
    private int Unidades;
    private int Precio;

    public Pedido (String nombreProducto, int unidades, int precio) {
        this.NombreProducto = nombreProducto;
        this.Unidades = unidades;
        this.Precio = precio;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public int getUnidades() {
        return Unidades;
    }

    public void setUnidades(int unidades) {
        Unidades = unidades;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }


}
