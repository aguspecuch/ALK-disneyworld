package ar.com.alkemy.disneyworld.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.entities.Personaje;
import ar.com.alkemy.disneyworld.entities.Serie;
import ar.com.alkemy.disneyworld.models.ContenidoModel;
import ar.com.alkemy.disneyworld.models.PersonajeModel;
import ar.com.alkemy.disneyworld.models.response.GenericResponse;
import ar.com.alkemy.disneyworld.models.response.PersonajeResponse;
import ar.com.alkemy.disneyworld.services.PeliculaService;
import ar.com.alkemy.disneyworld.services.PersonajeService;
import ar.com.alkemy.disneyworld.services.SerieService;

@RestController
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    SerieService serieService;

    @GetMapping("/characters")
    public ResponseEntity<List<PersonajeResponse>> listarPersonajes() {

        List<Personaje> personajes = personajeService.findAll();
        List<PersonajeResponse> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {

            PersonajeResponse p = new PersonajeResponse();
            p.imagen = personaje.getImagen();
            p.nombre = personaje.getNombre();

            lista.add(p);
        }

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/characters")
    public ResponseEntity<GenericResponse> create(@RequestBody PersonajeModel personajeModel) {

        List<Pelicula> peliculas = new ArrayList<>();
        List<Serie> series = new ArrayList<>();

        for (ContenidoModel pelicula : personajeModel.peliculas) {

            Pelicula p = peliculaService.findByPeliculaId(pelicula.id);
            peliculas.add(p);

        }

        for (ContenidoModel serie : personajeModel.series) {

            Serie s = serieService.findBySerieId(serie.id);
            series.add(s);

        }

        Personaje personaje = personajeService.create(personajeModel.nombre, personajeModel.imagen, personajeModel.edad,
                personajeModel.peso, personajeModel.historia, peliculas, series);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.id = personaje.getPersonajeId();
        r.message = "Personaje creado con exito.";

        return ResponseEntity.ok(r);
    }

    @GetMapping("/characters/details")
    public ResponseEntity<List<PersonajeModel>> detallarPersonajes() {

        List<Personaje> personajes = personajeService.findAll();
        List<PersonajeModel> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {

            PersonajeModel p = new PersonajeModel();
            p.imagen = personaje.getImagen();
            p.nombre = personaje.getNombre();
            p.edad = personaje.getEdad();
            p.historia = personaje.getHistoria();
            p.peso = personaje.getPeso();

            for (Pelicula pelicula : personaje.getPeliculas()) {

                ContenidoModel c = new ContenidoModel();
                c.id = pelicula.getPeliculaId();
                c.titulo = pelicula.getTitulo();

                p.peliculas.add(c);
            }

            for (Serie serie : personaje.getSeries()) {

                ContenidoModel c = new ContenidoModel();
                c.id = serie.getSerieId();
                c.titulo = serie.getTitulo();

                p.series.add(c);
            }

            lista.add(p);
        }

        return ResponseEntity.ok(lista);
    }

}
