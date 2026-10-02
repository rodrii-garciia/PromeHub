package es.promehub.backend;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class App {

    /*
    @FXML

    // 1. Cargar catálogo desde CSV
    private static void cargarCSV(){

        /*
            RF1. Cargar CSV
            La aplicación deberá:
                1. Comprobar que el fichero existe.
                2. Abrir el fichero.
                3. Leerlo secuencialmente.
                4. Ignorar la cabecera.
                5. Crear un objeto Videojuego por cada registro válido.
                6. Almacenar los objetos en una colección.
                7. Informar de los registros procesados.


        FileChooser fileChooser = new FileChooser();


        File archivo = fileChooser.showOpenDialog(
                cargarButton.getScene().getWindow()
        );


        if (archivo != null) {
            System.out.println("Archivo seleccionado:");
            System.out.println(archivo.getAbsolutePath());
        }
    }
*/
    @FXML
    // 2. Mostrar catálogo
    private static void mostrarCatalogo(){

    }

    @FXML
    // 3. Exportar catálogo a XML
    private static void exportarXML(){

    }

    @FXML
    // 4. Cargar catálogo desde XML
    private static void cargarXML(){

    }

    @FXML
    // 5. Exportar CSV
    private static void exportarCSV(){


    }

    @FXML
    // 6. Buscar videojuego
    private static void buscarVideojuego(){

    }

    @FXML
    // 7. Información de ficheros
    private static void infoFicheros(){

    }
}