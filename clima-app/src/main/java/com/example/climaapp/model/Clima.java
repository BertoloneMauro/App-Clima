package com.example.climaapp.model;

public class Clima {
    private String ciudad;
    private String descripcion;
    private double temperatura;

    public Clima(String ciudad, String descripcion, double temperatura) {
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.temperatura = temperatura;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTemperatura() {
        return temperatura;
    }
}
