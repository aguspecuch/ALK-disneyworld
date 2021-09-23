package ar.com.alkemy.disneyworld.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disneyworld.entities.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository <Pelicula, Integer>{
    
    Pelicula findByPeliculaId(Integer id);
}
