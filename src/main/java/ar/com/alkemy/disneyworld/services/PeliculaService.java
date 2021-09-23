package ar.com.alkemy.disneyworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.repos.PeliculaRepository;

@Service
public class PeliculaService {
    
    @Autowired
    PeliculaRepository peliculaRepo;

    public Pelicula findByPeliculaId(Integer id) {
        return peliculaRepo.findByPeliculaId(id);
    }
}
