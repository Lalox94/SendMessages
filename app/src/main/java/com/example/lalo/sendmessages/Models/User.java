package com.example.lalo.sendmessages.Models;



public class User {

    private String Name;
    private String Token;

    public User(String Name, String Token) {
        this.Name = Name;
        this.Token = Token;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
