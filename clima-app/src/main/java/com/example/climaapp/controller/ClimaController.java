package com.example.climaapp.controller;

import com.example.climaapp.model.WeatherApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ClimaController {

    private final String API_KEY = "e75480fa44eb427cbef225422252804";
    private final String URL = "http://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=";

    @GetMapping("/api/clima/{ciudad}")
    public Map<String, Object> obtenerClima(@PathVariable String ciudad) {

        RestTemplate restTemplate = new RestTemplate();
        WeatherApiResponse weatherApiResponse = restTemplate.getForObject(URL + ciudad, WeatherApiResponse.class);

        Map<String, Object> response = new HashMap<>();
        response.put("ciudad", weatherApiResponse.getLocation().getName());
        response.put("descripcion", weatherApiResponse.getCurrent().getCondition().getText());
        response.put("temperatura", weatherApiResponse.getCurrent().getTempC());
        response.put("humedad", weatherApiResponse.getCurrent().getHumidity());
        response.put("viento_kph", weatherApiResponse.getCurrent().getWindKph());

        return response;
    }
}
