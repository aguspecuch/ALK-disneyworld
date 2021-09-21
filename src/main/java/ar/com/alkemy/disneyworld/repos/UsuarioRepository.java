package ar.com.alkemy.disneyworld.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disneyworld.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {
    
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}
