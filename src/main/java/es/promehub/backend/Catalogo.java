package es.promehub.backend;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "catalogo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalogo {

    @XmlElement(name = "videojuego")
    private final List<Videojuego> videojuegos;

    public Catalogo() {
        videojuegos = new ArrayList<>();
    }

    public List<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void insertarVideojuego(Videojuego videojuego){

        videojuegos.add(videojuego);
    }
}