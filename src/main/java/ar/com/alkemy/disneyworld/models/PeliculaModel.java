package ar.com.alkemy.disneyworld.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeliculaModel {
    
    public Integer peliculaId;
    public String imagen;
    public String titulo;
    public Date fechaCreacion;
    public Integer calificacion;
    public List<String> personajes = new ArrayList<>();
    public String genero;

}
