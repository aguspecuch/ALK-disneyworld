package ar.com.alkemy.disneyworld.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.alkemy.disneyworld.entities.Personaje;
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
}
