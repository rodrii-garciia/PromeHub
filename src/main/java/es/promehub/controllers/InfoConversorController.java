package es.promehub.controllers;

import es.promehub.backend.Archivos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.File;
import java.io.IOException;

public class InfoConversorController {

    @FXML private VBox contenido;
    @FXML private VBox contenedorScroll;

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
    public void mostrarVistaCatalog() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/es/promehub/catalog_conversor.fxml")
        );

        Parent vista = loader.load();

        contenido.getChildren().setAll(vista);
    }

    @FXML
    public void initialize(){

        infoFicheros();
    }

    // lógica de negocio

    // 7. Información de ficheros
    private void infoFicheros(){

        for(File archivo : Archivos.archivos){

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/es/promehub/file_card.fxml")
            );

            try{
                Pane tarjeta = loader.load();

                FileCardController fileCardController = loader.getController();

                fileCardController.setDatos(archivo.getName(),
                        archivo.length(),
                        archivo.getAbsolutePath(),
                        archivo.exists()

                );

                contenedorScroll.getChildren().add(tarjeta);

            }catch(IOException e){

                System.out.println("Unable to create card.");
            }
        }
    }
}
