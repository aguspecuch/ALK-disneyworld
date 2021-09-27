package ar.com.alkemy.disneyworld.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.entities.Personaje;
import ar.com.alkemy.disneyworld.entities.Serie;
import ar.com.alkemy.disneyworld.repos.PersonajeRepository;

@Service
public class PersonajeService {

    @Autowired
    PersonajeRepository personajeRepo;

    @Autowired
    SerieService serieService;

    @Autowired
    PeliculaService peliculaService;

    public Personaje create(Personaje personaje) {
        return personajeRepo.save(personaje);
    }

    public Personaje create(String nombre, String imagen, Integer edad, Double peso, String historia,
            List<Pelicula> peliculas, List<Serie> series) {

        Personaje personaje = new Personaje();
        personaje.setNombre(nombre);
        personaje.setImagen(imagen);
        personaje.setEdad(edad);
        personaje.setPeso(peso);
        personaje.setHistoria(historia);

        for (Pelicula pelicula : peliculas) {
            personaje.agregarPelicula(pelicula);
        }

        for (Serie serie : series) {
            personaje.agregarSerie(serie);
        }

        return personajeRepo.save(personaje);
    }

    public Personaje update(Personaje personaje) {
        return personajeRepo.save(personaje);
    }

    public List<Personaje> findAll() {
        return personajeRepo.findAll();
    }

    public Personaje findById(Integer id) {
        return personajeRepo.findByPersonajeId(id);
    }

    public Personaje findByName(String nombre) {
        return personajeRepo.findByNombre(nombre);
    }

    public List<Personaje> findByEdad(Integer edad) {

        List<Personaje> p = new ArrayList<>();

        for (Personaje personaje : this.findAll()) {

            if (personaje.getEdad() == edad) {
                p.add(personaje);
            }
        }

        return p;
    }

    public List<Personaje> findByPeso(Double peso) {
        
        List<Personaje> p = new ArrayList<>();

        for (Personaje personaje : this.findAll()) {

            if (personaje.getPeso().doubleValue() == peso) {
                p.add(personaje);
            }
        }

        return p;

    }

    public List<Personaje> findBySerie(Integer serieId) {

        Serie s = serieService.findBySerieId(serieId);
        List<Personaje> p = new ArrayList<>();

        for (Personaje personaje : this.findAll()) {

            if (personaje.getSeries().contains(s)) {
                p.add(personaje);
            }
        }

        return p;
    }

    public List<Personaje> findByPelicula(Integer movieId) {

        Pelicula peli = peliculaService.findByPeliculaId(movieId);
        List<Personaje> p = new ArrayList<>();

        for (Personaje personaje : this.findAll()) {

            if (personaje.getPeliculas().contains(peli)) {
                p.add(personaje);
            }
        }

        return p;
    }

    public void delete(Personaje personaje) {
        personajeRepo.delete(personaje);
    }

    public boolean chequearDatos(Personaje personaje) {

        if (!chequearNombre(personaje.getNombre())) {
            return true;
        } else {
            return false;
        }

    }

    public boolean chequearNombre(String nombre) {

        Personaje p = findByName(nombre);

        if (p != null) {
            return false;
        } else {
            return true;
        }
    }

}
