package ar.com.alkemy.disneyworld.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disneyworld.entities.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository <Personaje, Integer> {
    
    Personaje findByPersonajeId(Integer id);
    Personaje findByNombre(String nombre);
    
}
