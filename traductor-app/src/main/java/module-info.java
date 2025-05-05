module org.maurobertolone.java.jdbc.traductorapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.maurobertolone.java.jdbc.traductorapp to javafx.fxml;
    exports org.maurobertolone.java.jdbc.traductorapp;
}