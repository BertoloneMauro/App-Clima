package com.example.climaapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {

    private Location location;
    private Current current;

    // Getters y Setters
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private String name;

        // Getters y Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {
        @JsonProperty("temp_c")
        private double tempC;

        @JsonProperty("feelslike_c")
        private double feelslike_c; // Sensación termica en celsius

        private int humidity;

        @JsonProperty("wind_kph")
        private double windKph;

        private Condition condition;

        // Getters y Setters
        public double getTempC() {
            return tempC;
        }

        public void setTempC(double tempC) {
            this.tempC = tempC;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getWindKph() {
            return windKph;
        }

        public void setWindKph(double windKph) {
            this.windKph = windKph;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public double getFeelslikeC() {
            return feelslike_c;  
        }

        public void setFeelslikeC(double feelslike_c) {
            this.feelslike_c = feelslike_c;
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition {
        private String text;

        // Getters y Setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
