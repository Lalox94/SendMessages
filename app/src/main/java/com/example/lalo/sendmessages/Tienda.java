package com.example.lalo.sendmessages;


public class Tienda {

    private String mName;
    private String mToken;

    public Tienda() { }

    public Tienda(String name, String token) {

        this.mName = name;
        this.mToken = token;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }


}
