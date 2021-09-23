package ar.com.alkemy.disneyworld.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="serie")
public class Serie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="serie_id")
    private Integer serieId;
    private String imagen;
    private String nombre;
    @Column(name="fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    private Integer calificacion;
    @ManyToMany
    @JoinTable(name = "serie_personaje", joinColumns = @JoinColumn(name = "serie_id"), inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personajes;
    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "genero_id")
    private Genero genero;

    public Integer getSerieId() {
        return serieId;
    }
    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
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
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Integer getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    public List<Personaje> getPersonajes() {
        return personajes;
    }
    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
        genero.getSeries().add(this);
    }
    
}
