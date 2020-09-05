package com.example.whinder;

public class WhinderList {
    String whinderedby ;
    String whinderis ;

    public WhinderList(String whinderedby, String whinderis) {
        this.whinderedby = whinderedby;
        this.whinderis = whinderis;
    }

    public WhinderList() {
    }

    public String getWhinderedby() {
        return whinderedby;
    }

    public void setWhinderedby(String whinderedby) {
        this.whinderedby = whinderedby;
    }

    public String getWhinderis() {
        return whinderis;
    }

    public void setWhinderis(String whinderis) {
        this.whinderis = whinderis;
    }
}
