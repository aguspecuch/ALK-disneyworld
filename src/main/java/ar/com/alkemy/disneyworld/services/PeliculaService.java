package ar.com.alkemy.disneyworld.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Pelicula;
import ar.com.alkemy.disneyworld.repos.PeliculaRepository;

@Service
public class PeliculaService {
    
    @Autowired
    PeliculaRepository peliculaRepo;

    public Pelicula create(Pelicula pelicula) {
        return peliculaRepo.save(pelicula);
    }

    public void update(Pelicula pelicula) {
        peliculaRepo.save(pelicula);
    }

    public void delete(Integer id) {
        Pelicula pelicula = this.findByPeliculaId(id);
        peliculaRepo.delete(pelicula);
    }

    public List<Pelicula> findAll() {
        return peliculaRepo.findAll();
    }

    public Pelicula findByPeliculaId(Integer id) {
        return peliculaRepo.findByPeliculaId(id);
    }

    public Pelicula findByTitulo(String titulo) {
        return peliculaRepo.findByTitulo(titulo);
    }
}
