package es.promehub.controllers;

import es.promehub.backend.Archivos;
import es.promehub.backend.Catalogo;
import es.promehub.backend.DatosApp;
import es.promehub.backend.Videojuego;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;

public class CsvConversorController {

    private static final Catalogo catalogo = DatosApp.catalogo;

    @FXML private VBox contenido;
    @FXML
    public void mostrarVistaXML() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/es/promehub/xml_conversor.fxml")
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
    // 1. Cargar catálogo desde CSV
    private void cargarCSV(){

        // Abrimos Filechooser para que directamente seleccione los csv
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV","*.csv"));
        File archivo = fileChooser.showOpenDialog(contenido.getScene().getWindow());

        if (archivo != null) {

            Archivos.archivos.add(archivo);

            // 1. Comprobar que el fichero existe.
            if(archivo.exists()){

                // 2. Abrir el fichero.
                // 3. Leerlo secuencialmente.
                try(FileReader fr = new FileReader(archivo);
                    BufferedReader br = new BufferedReader(fr)){

                    // 4. Ignorar la cabecera.
                    br.readLine();

                    // 5. Crear un objeto Videojuego por cada registro válido.
                    int contador = 0;
                    String linea;
                    while((linea = br.readLine()) != null){

                        String[] datos = linea.split(",");

                        // validación de atributos para videojuego
                        if (datos.length != 7) {
                            System.out.println("Invalid register: some atributes are missing");
                            continue;
                        }

                        Videojuego videojuego;
                        try{

                            videojuego = new Videojuego(
                                    Integer.parseInt(datos[0]),     // id
                                    datos[1],                       // título
                                    datos[2],                       // plataforma
                                    datos[3],                       // género
                                    Double.parseDouble(datos[4]),   // precio
                                    Integer.parseInt(datos[5]),     // stock
                                    datos[6]);                      // código proveedor

                            // 6. Almacenar los objetos en una colección.
                            catalogo.insertarVideojuego(videojuego);

                            contador++;

                        }catch(NumberFormatException e){

                            System.out.println("This CSV does not have the correct format");
                        }
                    }

                    // 7. Informar de los registros procesados.
                    System.out.println("CSV succesfully loaded. " + contador + " new insertions.");

                }catch(IOException e){
                    System.out.println("The file was unable to open");
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

    @FXML
    // 5. Exportar CSV
    private void exportarCSV(){

        String usuario = System.getProperty("user.home");
        File archivo = new File(usuario +  File.separator + "Downloads" + File.separator + "catalogo.csv");

            try(FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw)) {

                // como luego se ignorará la cabecera aquí se genera a propósito
                bw.write("id,titulo,plataforma,genero,precio,stock,codProveedor");
                bw.newLine();

                for(Videojuego videojuego : catalogo.getVideojuegos()){

                    String linea = videojuego.getId() + "," +
                            videojuego.getTitulo() + "," +
                            videojuego.getPlataforma() + "," +
                            videojuego.getGenero() + "," +
                            videojuego.getPrecio() + "," +
                            videojuego.getStock() + "," +
                            videojuego.getCodProveedor();

                    bw.write(linea);
                    bw.newLine();

                }

            }catch(IOException e){
                System.out.println("The file writing had an error");
            }

            Archivos.archivos.add(archivo);
            System.out.println("Catalog successfully exported");
        }

    }

