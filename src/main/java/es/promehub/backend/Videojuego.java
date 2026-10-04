package es.promehub.backend;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "videojuego")
@XmlAccessorType(XmlAccessType.FIELD)
public class Videojuego {

    @XmlAttribute
    private int id;
    @XmlElement
    private String titulo;

    @XmlElement
    private String plataforma;

    @XmlElement
    private String genero;

    @XmlElement
    private double precio;

    @XmlElement
    private int stock;

    @XmlTransient
    private String codProveedor;


    public Videojuego(int id, String titulo, String plataforma, String genero,
                      double precio, int stock, String codProveedor)
    {
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.genero = genero;
        this.precio = precio;
        this.stock = stock;
        this.codProveedor = codProveedor;
    }

    public Videojuego() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", genero='" + genero + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", codProveedor=" + codProveedor +
                '}';
    }
}
