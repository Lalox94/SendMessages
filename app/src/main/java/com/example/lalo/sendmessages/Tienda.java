package com.example.lalo.sendmessages;


public class Tienda {

    private String Nombre;
    private String Token;
    private String Distancia;
    private String Horario;

    public Tienda() {
    }  // Needed for Firebase


    public Tienda(String Distancia, String Horario, String Nombre, String Token) {

        this.Nombre = Nombre;
        this.Token = Token;
        this.Distancia = Distancia;
        this.Horario = Horario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getDistancia() {
        return Distancia;
    }

    public void setDistancia(String distancia) {
        Distancia = distancia;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }
}

