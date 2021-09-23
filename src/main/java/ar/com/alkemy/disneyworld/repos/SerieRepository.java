package ar.com.alkemy.disneyworld.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disneyworld.entities.Serie;

@Repository
public interface SerieRepository extends JpaRepository <Serie, Integer>{
    
    Serie findBySerieId(Integer id);
    
}
