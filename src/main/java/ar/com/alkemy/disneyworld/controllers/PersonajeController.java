package ar.com.alkemy.disneyworld.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    SerieService serieService;

    @GetMapping
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

    @PostMapping
    public ResponseEntity<GenericResponse> create(@RequestBody Personaje personaje) {

        personajeService.create(personaje);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.id = personaje.getPersonajeId();
        r.message = "Personaje creado con exito.";

        return ResponseEntity.ok(r);
    }

    @PostMapping("/2")
    public ResponseEntity<GenericResponse> create2(@RequestBody PersonajeModel personajeModel) {

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

    @GetMapping("/details")
    public ResponseEntity<List<PersonajeModel>> detallarPersonajes() {

        List<Personaje> personajes = personajeService.findAll();
        List<PersonajeModel> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {

            PersonajeModel p = this.detallarPersonaje(personaje);
            lista.add(p);

        }

        return ResponseEntity.ok(lista);

    }

    @DeleteMapping
    public ResponseEntity<GenericResponse> delete(@RequestParam(value = "id") Integer id) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {

            personajeService.delete(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Personaje eliminado con exito";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "El id ingresado no corresponde a ningun personaje.";

        return ResponseEntity.badRequest().body(r);
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> findByNombre(@RequestParam(value = "name") String name) {

        Personaje personaje = personajeService.findByName(name);

        if (personaje != null) {

            PersonajeModel p = this.detallarPersonaje(personaje);

            return ResponseEntity.ok(p);
        }

        GenericResponse r = new GenericResponse();
        r.isOk = false;
        r.message = "Ese nombre no corresponde a ningun personaje.";

        return ResponseEntity.badRequest().body(r);

    }

    @GetMapping(params = "age")
    public ResponseEntity<?> findByEdad(@RequestParam(value = "age") Integer age) {

        List<Personaje> personajes = personajeService.findByEdad(age);

        if (personajes.isEmpty()) {

            GenericResponse r = new GenericResponse();
            r.isOk = false;
            r.message = "No existe ningun personaje de esa edad.";

            return ResponseEntity.badRequest().body(r);
        }

        List<PersonajeModel> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {

            PersonajeModel p = this.detallarPersonaje(personaje);
            lista.add(p);
        }

        return ResponseEntity.ok(lista);

    }

    @GetMapping(params = "peso")
    public ResponseEntity<?> findByPeso(@RequestParam(value = "peso") Double peso) {

        List<Personaje> personajes = personajeService.findByPeso(peso);

        if (personajes.isEmpty()) {

            GenericResponse r = new GenericResponse();
            r.isOk = false;
            r.message = "No existe ningun personaje con ese peso.";

            return ResponseEntity.badRequest().body(r);
        }

        List<PersonajeModel> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {

            PersonajeModel p = this.detallarPersonaje(personaje);
            lista.add(p);
        }

        return ResponseEntity.ok(lista);

    }

    @GetMapping(params = "series")
    public ResponseEntity<?> findBySerie(@RequestParam(value = "series") Integer serieId) {

        List<Personaje> personajes = personajeService.findBySerie(serieId);

        if (personajes.isEmpty()) {

            GenericResponse r = new GenericResponse();
            r.isOk = false;
            r.message = "No existe ningun personaje registrado de esa serie";

            return ResponseEntity.badRequest().body(r);
        }

        List<PersonajeModel> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {

            PersonajeModel p = this.detallarPersonaje(personaje);
            lista.add(p);
        }

        return ResponseEntity.ok(lista);

    }

    @GetMapping(params = "movies")
    public ResponseEntity<?> findByPelicula(@RequestParam(value = "movies") Integer movieId) {

        List<Personaje> personajes = personajeService.findByPelicula(movieId);

        if (personajes.isEmpty()) {

            GenericResponse r = new GenericResponse();
            r.isOk = false;
            r.message = "No existe ningun personaje registrado de esa pelicula.";

            return ResponseEntity.badRequest().body(r);
        }

        List<PersonajeModel> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {

            PersonajeModel p = this.detallarPersonaje(personaje);
            lista.add(p);
        }

        return ResponseEntity.ok(lista);

    }

    public PersonajeModel detallarPersonaje(Personaje personaje) {

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

        return p;
    }

}
