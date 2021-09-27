package ar.com.alkemy.disneyworld.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Genero;
import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.entities.Personaje;
import ar.com.alkemy.disneyworld.repos.PeliculaRepository;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRepository peliculaRepo;

    @Autowired
    PersonajeService personajeservice;

    @Autowired
    GeneroService generoService;

    public Pelicula create(Pelicula pelicula) {
        return peliculaRepo.save(pelicula);
    }

    public Pelicula create(String titulo, String imagen, Date fechaCreacion, Integer calificacion,
            List<String> personajes, String genero) {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        pelicula.setImagen(imagen);
        pelicula.setFechaCreacion(fechaCreacion);
        pelicula.setCalificacion(calificacion);

        Genero g = generoService.findByNombre(genero);
        pelicula.setGenero(g);

        for (String personaje : personajes) {

            Personaje p = personajeservice.findByName(personaje);
            pelicula.getPersonajes().add(p);

        }

        if ( !this.chequearDatos(pelicula) ) {
            return null;
        }

        return peliculaRepo.save(pelicula);

    }

    public void update(Pelicula pelicula) {
        peliculaRepo.save(pelicula);
    }

    public void delete(Pelicula pelicula) {

        for (Personaje personaje : pelicula.getPersonajes()) {
            personaje.getPeliculas().remove(pelicula);
        }

        peliculaRepo.delete(pelicula);
    }

    public List<Pelicula> findAll() {
        return peliculaRepo.findAll();
    }

    public Pelicula findByPeliculaId(Integer id) {
        return peliculaRepo.findByPeliculaId(id);
    }

    public Pelicula findByTitulo(String titulo) {
        return peliculaRepo.findByTitulo(titulo);
    }

    public List<Pelicula> findByGenero(Integer generoId) {

        Genero genero = generoService.findByGeneroId(generoId);

        List<Pelicula> lista = new ArrayList<>();

        for (Pelicula pelicula : this.findAll()) {

            if (pelicula.getGenero().equals(genero)) {
                lista.add(pelicula);
            }
        }

        return lista;

    }

    public List<Pelicula> ordenarAsc() {

        List<Pelicula> lista = this.findAll();
        lista.sort(Comparator.comparing(Pelicula::getFechaCreacion));

        return lista;

    }

    public List<Pelicula> ordenarDesc() {

        List<Pelicula> lista = this.findAll();
        lista.sort(Comparator.comparing(Pelicula::getFechaCreacion).reversed());

        return lista;

    }

    public boolean chequearDatos(Pelicula pelicula) {

        if ( this.findByTitulo(pelicula.getTitulo()) != null)  {
            return false;
        }

        return true;
    }
}
