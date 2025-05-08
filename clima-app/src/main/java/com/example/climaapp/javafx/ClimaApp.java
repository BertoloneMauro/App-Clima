package com.example.climaapp.javafx;

import com.example.climaapp.model.Clima;
import com.example.climaapp.service.ClimaService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ClimaApp extends Application {

    private Label temperaturaLabel;
    private ImageView iconoClima;
    private VBox datosExtras;

    private ClimaService climaService = new ClimaService(); 

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        Stop[] stops = new Stop[] {
                new Stop(0, Color.web("#0066cc")),
                new Stop(1, Color.web("#87cefa"))
        };
        LinearGradient gradient = new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops
        );
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, null)));

        // Campo de búsqueda
        TextField ciudadInput = new TextField();
        ciudadInput.setPromptText("Ingresá una ciudad");
        ciudadInput.setMaxWidth(200);

        Button buscarBtn = new Button("Buscar");

        HBox buscador = new HBox(10, ciudadInput, buscarBtn);
        buscador.setAlignment(Pos.CENTER);

        // Temperatura e ícono
        temperaturaLabel = new Label("--°C");
        temperaturaLabel.setFont(Font.font("Arial", 60));
        temperaturaLabel.setTextFill(Color.WHITE);

        iconoClima = new ImageView();
        iconoClima.setFitHeight(80);
        iconoClima.setFitWidth(80);

        HBox climaPrincipal = new HBox(10, temperaturaLabel, iconoClima);
        climaPrincipal.setAlignment(Pos.CENTER);

        // Demas datos
        datosExtras = new VBox(5);
        datosExtras.setAlignment(Pos.CENTER);
        actualizarDatos("Ciudad: ---", "Humedad: --%", "Sensación térmica: --°C", "Viento: -- km/h");

        // Acción del boton
        buscarBtn.setOnAction(e -> {
            String ciudad = ciudadInput.getText().trim();
            if (!ciudad.isEmpty()) {
                // Llamamos al servicio para obtener el clima de la ciudad
                Clima clima = climaService.obtenerClimaDeCiudad(ciudad);

                // Actualizamos los datos en la UI
                temperaturaLabel.setText(clima.getTemperatura() + "°C");
                iconoClima.setImage(new Image("https://openweathermap.org/img/wn/01d@2x.png"));  // Reemplaza con icono real si es necesario

                
                actualizarDatos(
                        "Ciudad: " + clima.getCiudad(),
                        "Descripción: " + clima.getDescripcion(),
                        "Humedad: " + clima.getHumedad() + "%",
                        "Sensación Térmica: " + clima.getSensacionTermica() + "°C",
                        "Viento: " + clima.getViento() + " km/h"
                );
            }
        });


        root.getChildren().addAll(buscador, climaPrincipal, datosExtras);

 
        Scene scene = new Scene(root, 500, 450);
        stage.setTitle("Clima Actual");
        stage.setScene(scene);
        stage.show();
    }

    private void actualizarDatos(String... datos) {
        datosExtras.getChildren().clear();
        for (String d : datos) {
            Label label = new Label(d);
            label.setFont(Font.font("Arial", 14));
            label.setTextFill(Color.WHITE);
            datosExtras.getChildren().add(label);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
