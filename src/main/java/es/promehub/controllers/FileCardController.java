package es.promehub.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class FileCardController {

    @FXML Label nomArchivo;
    @FXML Label tamanyo;
    @FXML Label ruta;
    @FXML Label estado;

    public void setDatos(String nomArchivo, long tamanyo, String ruta, boolean estado){

        this.nomArchivo.setText(nomArchivo);
        this.tamanyo.setText("Size: " + tamanyo + " bytes");
        this.ruta.setText("Path: " + ruta);
        this.estado.setText("Exists: " + estado);
    }
}
