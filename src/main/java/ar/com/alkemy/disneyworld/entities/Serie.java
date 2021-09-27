package ar.com.alkemy.disneyworld.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String titulo;
    @Column(name="fecha_de_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    private Integer calificacion;
    @ManyToMany(mappedBy = "series", fetch = FetchType.LAZY)
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
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
