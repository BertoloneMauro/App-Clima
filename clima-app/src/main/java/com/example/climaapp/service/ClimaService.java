package com.example.climaapp.service;

import com.example.climaapp.model.Clima;
import com.example.climaapp.model.WeatherApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ClimaService {

    private static final String API_KEY = "e75480fa44eb427cbef225422252804"; // Cambiar por tu nueva API Key
    private static final String BASE_URL = "http://api.weatherapi.com/v1/current.json";

    private final RestTemplate restTemplate = new RestTemplate();

    public Clima obtenerClimaDeCiudad(String ciudad) {
        // Codificar la ciudad para manejar espacios y caracteres especiales
        String ciudadCodificada = null;
        try {
            ciudadCodificada = URLEncoder.encode(ciudad, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // En caso de error en la codificación, puedes devolver un error o una ciudad no válida
            return new Clima(ciudad, "Error al codificar la ciudad", 0.0);
        }

        // Generar la URL con la ciudad codificada
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("key", API_KEY)
                .queryParam("q", ciudadCodificada)  // Usar la ciudad codificada
                .queryParam("lang", "es")
                .toUriString();

        // Imprimir la URL generada para depuración
        System.out.println("🌎 URL generada: " + url);

        try {
            // Solicitar datos a la API
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (response != null) {
                return new Clima(
                        response.getLocation().getName(),
                        response.getCurrent().getCondition().getText(),
                        response.getCurrent().getTempC()
                );
            } else {
                return new Clima(ciudad, "Datos no disponibles", 0.0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Clima(ciudad, "Error al obtener datos", 0.0);
        }
    }
}
