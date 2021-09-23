package ar.com.alkemy.disneyworld.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disneyworld.entities.Genero;

@Repository
public interface GeneroRepository extends JpaRepository <Genero, Integer>{
    
    Genero findByNombre(String nombre);
}
