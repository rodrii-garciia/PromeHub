package es.promehub.controllers;

import es.promehub.backend.Catalogo;
import es.promehub.backend.DatosApp;
import es.promehub.backend.Videojuego;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CatalogConversorController {

    private static final Catalogo catalogo = DatosApp.catalogo;

    @FXML private VBox contenido;
    @FXML private TextField buscador;
    @FXML private TableView<Videojuego> tabla;

    @FXML private TableColumn<Videojuego, Integer> colId;
    @FXML private TableColumn<Videojuego, String> colTitulo;
    @FXML private TableColumn<Videojuego, String> colPlataforma;
    @FXML private TableColumn<Videojuego, String> colGenero;
    @FXML private TableColumn<Videojuego, Double> colPrecio;
    @FXML private TableColumn<Videojuego, Integer> colStock;
    @FXML private TableColumn<Videojuego, String> colCodProveedor;
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

    @FXML
    public void initialize() {
        mostrarCatalogo();
    }

    @FXML
    public void borrarContenido(){

        buscador.setText("");
    }

    // lógica de negocio

    // 2. Mostrar catálogo
    // 6. Buscar videojuego
    private void mostrarCatalogo(){

        // hacemos un observable arraylist para trabajar con javaFX
        ObservableList<Videojuego> videojuegos = FXCollections.observableArrayList(catalogo.getVideojuegos());
        FilteredList<Videojuego> filtrados = new FilteredList<>(videojuegos);
        buscador.textProperty().addListener((observable, oldValue, newValue) -> {

            String texto = newValue.trim().toLowerCase();
            filtrados.setPredicate(videojuego -> {

                if(texto.isEmpty()){
                    return true;
                }
                else{
                    return String.valueOf(videojuego.getId()).equals(texto) ||
                            videojuego.getTitulo().toLowerCase().contains(texto);
                }
            });
        });

        // le pasamos el array con el que queremos trabajar a TableView
        tabla.setItems(filtrados);

        // definimos las reglas con las que queremos que se rellenen las columnas
        colId.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getId()));
        colTitulo.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getTitulo()));
        colPlataforma.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getPlataforma()));
        colGenero.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getGenero()));
        colPrecio.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getPrecio()));
        colStock.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getStock()));
        colCodProveedor.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getCodProveedor()));
    }
}
