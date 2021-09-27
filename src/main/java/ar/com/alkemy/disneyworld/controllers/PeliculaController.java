package ar.com.alkemy.disneyworld.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.entities.Personaje;
import ar.com.alkemy.disneyworld.models.PeliculaModel;
import ar.com.alkemy.disneyworld.models.response.GenericResponse;
import ar.com.alkemy.disneyworld.models.response.PeliculaResponse;
import ar.com.alkemy.disneyworld.services.PeliculaService;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaResponse>> listar() {

        List<Pelicula> peliculas = peliculaService.findAll();
        List<PeliculaResponse> lista = new ArrayList<>();

        for (Pelicula pelicula : peliculas) {
            PeliculaResponse p = new PeliculaResponse();
            p.imagen = pelicula.getImagen();
            p.titulo = pelicula.getTitulo();
            p.fechaCreacion = pelicula.getFechaCreacion();

            lista.add(p);
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/details")
    public ResponseEntity<List<PeliculaModel>> listarDetalles() {

        List<PeliculaModel> lista = new ArrayList<>();

        for (Pelicula pelicula : peliculaService.findAll()) {

            PeliculaModel p = detallarPelicula(pelicula);
            lista.add(p);

        }

        return ResponseEntity.ok(lista);

    }

    @PostMapping
    public ResponseEntity<GenericResponse> create(@RequestBody PeliculaModel pelicula) {

        Pelicula p = peliculaService.create(pelicula.titulo, pelicula.imagen, pelicula.fechaCreacion,
                pelicula.calificacion, pelicula.personajes, pelicula.genero);
        
        GenericResponse r = new GenericResponse();

        if ( p != null ) {

            r.isOk = true;
            r.id = p.getPeliculaId();
            r.message = "Pelicula creada con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "Ya se encuentra registrada una pelicula/serie con el mismo titulo.";

        return ResponseEntity.badRequest().body(r);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<GenericResponse> delete(@RequestParam(value="id") Integer id) {

        Pelicula pelicula = peliculaService.findByPeliculaId(id);
        GenericResponse r = new GenericResponse();

        if (pelicula != null) {

            peliculaService.delete(pelicula);

            r.isOk = true;
            r.id = pelicula.getPeliculaId();
            r.message = "Pelicula/serie eliminada con exito";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "El id ingresado no corresponde a ninguna pelicula/serie.";

        return ResponseEntity.badRequest().body(r);
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> findByNombre(@RequestParam(value = "name") String name) {

        Pelicula pelicula = peliculaService.findByTitulo(name);

        if (pelicula != null) {

            PeliculaModel p = this.detallarPelicula(pelicula);

            return ResponseEntity.ok(p);

        }

        GenericResponse r = new GenericResponse(false, "Ese nombre no corresponde a ninguna pelicula.");

        return ResponseEntity.badRequest().body(r);

    }

    @GetMapping(params = "genre")
    public ResponseEntity<?> findByGenero(@RequestParam(value = "genre") Integer genreId) {

        List<Pelicula> peliculas = peliculaService.findByGenero(genreId);

        if (peliculas.isEmpty()) {

            GenericResponse r = new GenericResponse(false, "No existe ninguna pelicula registrada con ese genero.");

            return ResponseEntity.badRequest().body(r);
        }

        List<PeliculaModel> lista = new ArrayList<>();

        for (Pelicula pelicula : peliculas) {

            PeliculaModel p = this.detallarPelicula(pelicula);
            lista.add(p);
        }

        return ResponseEntity.ok(lista);

    }

    @GetMapping(params = "order")
    public ResponseEntity<?> ordenar(@RequestParam(value = "order") String order) {

        List<PeliculaModel> lista = new ArrayList<>();

        if (order.equals("ASC")) {

            for (Pelicula pelicula : peliculaService.ordenarAsc()) {

                PeliculaModel p = this.detallarPelicula(pelicula);
                lista.add(p);

            }

            return ResponseEntity.ok(lista);

        } else if (order.equals("DESC")) {

            for (Pelicula pelicula : peliculaService.ordenarDesc()) {

                PeliculaModel p = this.detallarPelicula(pelicula);
                lista.add(p);

            }

            return ResponseEntity.ok(lista);
        }

        GenericResponse r = new GenericResponse(false,
                "ERROR. Ingrese 'ASC' para ordenar de forma ascendente o 'DESC' de forma descendente");

        return ResponseEntity.badRequest().body(r);

    }

    public PeliculaModel detallarPelicula(Pelicula pelicula) {

        PeliculaModel p = new PeliculaModel();
        p.peliculaId = pelicula.getPeliculaId();
        p.titulo = pelicula.getTitulo();
        p.imagen = pelicula.getImagen();
        p.fechaCreacion = pelicula.getFechaCreacion();
        p.calificacion = pelicula.getCalificacion();
        p.genero = pelicula.getGenero().getNombre();

        for (Personaje personaje : pelicula.getPersonajes()) {

            p.personajes.add(personaje.getNombre());
        }

        return p;
    }

}
