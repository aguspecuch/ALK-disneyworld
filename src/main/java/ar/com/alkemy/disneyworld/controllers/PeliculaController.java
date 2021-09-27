package ar.com.alkemy.disneyworld.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.entities.Personaje;
import ar.com.alkemy.disneyworld.models.PeliculaModel;
import ar.com.alkemy.disneyworld.models.response.PeliculaResponse;
import ar.com.alkemy.disneyworld.services.PeliculaService;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaModel>> listar() {

        List<Pelicula> peliculas = peliculaService.findAll();
        List<PeliculaModel> lista = new ArrayList<>();

        for (Pelicula pelicula : peliculas) {
            PeliculaModel p = new PeliculaModel();
            p.imagen = pelicula.getImagen();
            p.titulo = pelicula.getTitulo();
            p.fechaCreacion = pelicula.getFechaCreacion();

            lista.add(p);
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/details")
    public ResponseEntity<List<PeliculaResponse>> listarDetalles() {

        List<PeliculaResponse> lista = new ArrayList<>();

        for (Pelicula pelicula : peliculaService.findAll()) {

            PeliculaResponse p = new PeliculaResponse();
            p.peliculaId = pelicula.getPeliculaId();
            p.titulo = pelicula.getTitulo();
            p.imagen = pelicula.getImagen();
            p.fechaCreacion = pelicula.getFechaCreacion();
            p.calificacion = pelicula.getCalificacion();
            p.genero = pelicula.getGenero().getNombre();

            for (Personaje personaje : pelicula.getPersonajes()) {

                p.personajes.add(personaje.getNombre());
            }

            lista.add(p);

        }

        return ResponseEntity.ok(lista);

    }

}
