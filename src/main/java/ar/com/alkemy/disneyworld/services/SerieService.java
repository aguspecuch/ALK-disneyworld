package ar.com.alkemy.disneyworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Serie;
import ar.com.alkemy.disneyworld.repos.SerieRepository;

@Service
public class SerieService {
    
    @Autowired
    SerieRepository serieRepo;

    public Serie findBySerieId(Integer id) {
        return serieRepo.findBySerieId(id);
    }

    public Serie findByTitulo(String titulo) {
        return serieRepo.findByTitulo(titulo);
    }
}
