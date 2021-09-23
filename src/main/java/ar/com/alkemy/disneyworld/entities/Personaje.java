package ar.com.alkemy.disneyworld.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="personajes")
public class Personaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="personaje_id")
    private Integer personajeId;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    @ManyToMany(mappedBy = "personajes")
    private List<Pelicula> peliculas = new ArrayList<>();
    @ManyToMany(mappedBy = "personajes")
    private List<Serie> series = new ArrayList<>();

    public Integer getPersonajeId() {
        return personajeId;
    }
    public void setPersonajeId(Integer personajeId) {
        this.personajeId = personajeId;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public String getHistoria() {
        return historia;
    }
    public void setHistoria(String historia) {
        this.historia = historia;
    }
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }
    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    public List<Serie> getSeries() {
        return series;
    }
    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    // RELACION BIDIRECCIONAL

    public void agregarPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
        pelicula.getPersonajes().add(this);
    }

    public void agregarSerie(Serie serie) {
        this.series.add(serie);
        serie.getPersonajes().add(this);
    }

    
}
