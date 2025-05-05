package com.example.climaapp.model;

public class Clima {
    private String ciudad;
    private String descripcion;
    private double temperatura;
    private double humedad;
    private double sensacionTermica;
    private double viento;

    public Clima(String ciudad, String descripcion, double temperatura, double humedad, double sensacionTermica, double viento) {
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.sensacionTermica = sensacionTermica;
        this.viento = viento;
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

    public double getHumedad() {
        return humedad;
    }

    public double getSensacionTermica() {
        return sensacionTermica;
    }

    public double getViento() {
        return viento;
    }
}
