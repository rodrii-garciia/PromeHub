package es.promehub.controllers;

import es.promehub.backend.Archivos;
import es.promehub.backend.Catalogo;
import es.promehub.backend.DatosApp;
import es.promehub.backend.Videojuego;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XmlConversorController {

    private static final Catalogo catalogo = DatosApp.catalogo;

    @FXML private VBox contenido;
    // fxml transition functions
    @FXML
    public void mostrarVistaCSV() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/es/promehub/csv_conversor.fxml")
        );

        Parent vista = loader.load();

        contenido.getChildren().setAll(vista);
    }
    @FXML
    public void mostrarVistaCatalogo() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/es/promehub/catalog_conversor.fxml")
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

    // lógica de negocio

    @FXML
    // 3. Exportar catálogo a XML
    private void exportarXML(){

        String usuario = System.getProperty("user.home");
        File archivo = new File(usuario +  File.separator + "Downloads" + File.separator + "catalogo.xml");

        // estoy es innecesario porque marshaller.marshal() ya puede crear el archivo si no existe
        /* if(!archivo.exists()){
            try{
                archivo.createNewFile();
            } catch(IOException e){
                System.out.println("The file could not be created");
            }
        } */

        try{
            JAXBContext contexto = JAXBContext.newInstance(Catalogo.class);
            Marshaller marshaller = contexto.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(catalogo, archivo);

            Archivos.archivos.add(archivo);
            System.out.println("Catalog successfully exported");

        }catch(JAXBException e){
            System.out.println("The catalog could not be exported");
        }

    }

    @FXML
    // 4. Cargar catálogo desde XML
    private void cargarXML(){

        // Abrimos Filechooser para que directamente seleccione el xml
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos XML","*.xml"));
        File archivo = fileChooser.showOpenDialog(contenido.getScene().getWindow());

        if (archivo != null) {

            if(archivo.exists()){

                Archivos.archivos.add(archivo);

                try{
                    JAXBContext contexto = JAXBContext.newInstance(Catalogo.class);
                    Unmarshaller unmarshaller = contexto.createUnmarshaller();
                    Catalogo catalogoImportado = (Catalogo) unmarshaller.unmarshal(archivo);

                    // insertamos cada videojuego en el catálogo original
                    for (Videojuego videojuego : catalogoImportado.getVideojuegos()) {
                        catalogo.insertarVideojuego(videojuego);
                    }

                    System.out.println("The XML was successfully loaded");

                }catch(JAXBException e){
                    System.out.println("The XML was unable to load");
                }

            }
            else{
                System.out.println("The file does not exist");
            }
        }
        else{
            System.out.println("The file cannot be null");
        }

    }
}
