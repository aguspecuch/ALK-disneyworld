package ar.com.alkemy.disneyworld.models.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeliculaResponse {
    
    public Integer peliculaId;
    public String imagen;
    public String titulo;
    public Date fechaCreacion;
    public Integer calificacion;
    public List<String> personajes = new ArrayList<>();
    public String genero;

}
