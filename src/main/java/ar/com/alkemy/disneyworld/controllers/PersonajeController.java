package ar.com.alkemy.disneyworld.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.entities.Personaje;
import ar.com.alkemy.disneyworld.entities.Serie;
import ar.com.alkemy.disneyworld.models.response.GenericResponse;
import ar.com.alkemy.disneyworld.models.response.PersonajeResponse;
import ar.com.alkemy.disneyworld.services.PersonajeService;

@RestController
public class PersonajeController {

    @Autowired
    PersonajeService personajeService;

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

    @GetMapping("/characters/all")
    public ResponseEntity<List<Personaje>> listarTodoPersonajes() {

        return ResponseEntity.ok(personajeService.findAll());
    }

    @PostMapping("/characters")
    public ResponseEntity<GenericResponse> create(@RequestBody Personaje personaje) {

        GenericResponse r = new GenericResponse();

        if (personajeService.chequearDatos(personaje)) {

            Personaje p = personajeService.create(personaje);

            r.id = p.getPersonajeId();
            r.isOk = true;
            r.message = "Personaje creado con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "ERROR. Personaje ya registrado.";

        return ResponseEntity.badRequest().body(r);
    }

    @PutMapping("/characters/name")
    public ResponseEntity<GenericResponse> actualizarNombre(@RequestParam Integer id, @RequestBody String name) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {
            personaje.setNombre(name);
            personajeService.update(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Nombre actualizado con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }

    @PutMapping("/characters/edad")
    public ResponseEntity<GenericResponse> actualizarEdad(@RequestParam Integer id, @RequestBody Integer edad) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {
            personaje.setEdad(edad);
            personajeService.update(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Edad actualizada con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }

    @PutMapping("/characters/imagen")
    public ResponseEntity<GenericResponse> actualizarImagen(@RequestParam Integer id, @RequestBody String imagen) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {
            personaje.setImagen(imagen);
            personajeService.update(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Imagen actualizada con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }

    @PutMapping("/characters/peso")
    public ResponseEntity<GenericResponse> actualizarPeso(@RequestParam Integer id, @RequestBody Double peso) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {
            personaje.setPeso(peso);
            personajeService.update(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Peso actualizado con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }

    @PutMapping("/characters/historia")
    public ResponseEntity<GenericResponse> actualizarHistoria(@RequestParam Integer id, @RequestBody String historia) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {
            personaje.setHistoria(historia);
            personajeService.update(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Historia actualizada con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }

    @PutMapping("/characters/peliculas")
    public ResponseEntity<GenericResponse> agregarPelicula(@RequestParam Integer id, @RequestBody Pelicula pelicula) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {
            personaje.agregarPelicula(pelicula);
            personajeService.update(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Pelicula agregada con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }

    @PutMapping("/characters/series")
    public ResponseEntity<GenericResponse> agregarSerie(@RequestParam Integer id, @RequestBody Serie serie) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {
            personaje.agregarSerie(serie);
            personajeService.update(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Serie agregada con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }

    @DeleteMapping("/characters")
    public ResponseEntity<GenericResponse> delete(@RequestParam Integer id) {

        Personaje personaje = personajeService.findById(id);
        GenericResponse r = new GenericResponse();

        if (personaje != null) {

            personajeService.delete(personaje);

            r.isOk = true;
            r.id = personaje.getPersonajeId();
            r.message = "Personaje eliminado con exito.";

            return ResponseEntity.ok(r);

        }

        r.isOk = false;
        r.message = "No se encontro ningun personaje con ese id.";

        return ResponseEntity.badRequest().body(r);
    }
}
