package ar.com.alkemy.disneyworld.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="genero")
public class Genero {
    
    @Id
    @Column(name="genero_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer generoId;
    private String nombre;
    private String imagen;
    @OneToMany(mappedBy = "genero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pelicula> peliculas = new ArrayList<>();
    
    public Integer getGeneroId() {
        return generoId;
    }
    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }
    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

}
