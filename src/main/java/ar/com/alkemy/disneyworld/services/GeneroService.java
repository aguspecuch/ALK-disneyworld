package ar.com.alkemy.disneyworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Genero;
import ar.com.alkemy.disneyworld.repos.GeneroRepository;

@Service
public class GeneroService {
    
    @Autowired
    GeneroRepository generoRepo;

    public Genero findByGeneroId(Integer id) {
        return generoRepo.findByGeneroId(id);
    }

    public Genero findByNombre(String nombre) {
        return generoRepo.findByNombre(nombre);
    }
}
