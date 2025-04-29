package com.example.climaapp.javafx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClimaApp extends Application {

    private TextField ciudadInput;
    private TextArea resultadoArea;


    @Override
    public void start(Stage primaryStage) {
        Label ciudadLabel = new Label("Ingrese la ciudad:");
        ciudadInput = new TextField();
        Button buscarButton = new Button("Buscar Clima");
        resultadoArea = new TextArea();
        resultadoArea.setEditable(false);

        buscarButton.setOnAction(e -> buscarClima());

        VBox root = new VBox(10, ciudadLabel, ciudadInput, buscarButton, resultadoArea);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("App de Clima");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buscarClima() {
        String ciudad = ciudadInput.getText().trim();
        if (ciudad.isEmpty()) {
            resultadoArea.setText("Por favor, ingrese una ciudad.");
            return;
        }

        Task<Void> task = new Task<Void>() {
            @Override

            protected Void call() throws Exception {
                String ciudadCodificada = URLEncoder.encode(ciudad, StandardCharsets.UTF_8);
                try {
                    String urlString = "http://localhost:8080/api/clima/" + ciudadCodificada;
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    Scanner scanner = new Scanner(conn.getInputStream());
                    StringBuilder json = new StringBuilder();
                    while (scanner.hasNext()) {
                        json.append(scanner.nextLine());
                    }
                    scanner.close();

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> datos = mapper.readValue(json.toString(), Map.class);

                    String resultado = "Ciudad: " + datos.get("ciudad") + "\n"
                            + "Descripción: " + datos.get("descripcion") + "\n"
                            + "Temperatura: " + datos.get("temperatura") + " °C\n"
                            + "Humedad: " + datos.get("humedad") + "%\n"
                            + "Viento: " + datos.get("viento_kph") + " km/h";

                    updateMessage(resultado);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    updateMessage("Error al obtener el clima.");
                }
                return null;
            }
        };

        resultadoArea.textProperty().bind(task.messageProperty());
        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
