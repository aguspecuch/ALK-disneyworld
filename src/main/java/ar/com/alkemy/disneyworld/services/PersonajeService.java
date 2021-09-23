package ar.com.alkemy.disneyworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Personaje;
import ar.com.alkemy.disneyworld.repos.PersonajeRepository;

@Service
public class PersonajeService {
    
    @Autowired
    PersonajeRepository personajeRepo;

    public Personaje create(Personaje personaje) {
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

    public void delete(Personaje personaje) {
        personajeRepo.delete(personaje);
    }
}
