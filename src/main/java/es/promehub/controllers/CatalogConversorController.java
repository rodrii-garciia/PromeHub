package es.promehub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CatalogConversorController {

    @FXML
    private VBox contenido;

    @FXML
    public void mostrarVistaCSV() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/es/promehub/csv_conversor.fxml")
        );

        Parent vista = loader.load();

        contenido.getChildren().setAll(vista);
    }
    @FXML
    public void mostrarVistaXML() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/es/promehub/xml_conversor.fxml")
        );

        Parent vista = loader.load();

        contenido.getChildren().setAll(vista);
    }

    @FXML
    public void mostrarVistaInfo() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/es/promehub/info_conversor.fxml")
        );

        Parent vista = loader.load();

        contenido.getChildren().setAll(vista);
    }
}
