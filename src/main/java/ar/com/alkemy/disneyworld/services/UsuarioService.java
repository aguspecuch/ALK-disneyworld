package ar.com.alkemy.disneyworld.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disneyworld.entities.Usuario;
import ar.com.alkemy.disneyworld.repos.UsuarioRepository;
import ar.com.alkemy.disneyworld.security.Crypto;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repo;

    public Usuario registrar(String fullName, String username, String password, String email) {

        Usuario u = new Usuario();
        u.setFullName(fullName);
        u.setUsername(username);
        u.setPassword(Crypto.encrypt(password, email.toLowerCase()));
        u.setEmail(email);

        return repo.save(u);
    }

    public boolean validarDatosUsuario(Usuario usuario) {

        if (buscarPorUsername(usuario.getUsername()) != null) {
            return false;
        }
        if (buscarPorEmail(usuario.getEmail()) != null) {
            return false;
        }

        return true;
    }

    public Usuario login(String username, String password) {

        Usuario u = buscarPorUsername(username);

        if (u == null || !u.getPassword().equals(Crypto.encrypt(password, u.getEmail().toLowerCase()))) {

            throw new BadCredentialsException("Usuario o contraseña invalida");
        }

        return u;

    }

    public Usuario buscarPorUsername(String username) {
        return repo.findByUsername(username);
    }

    public Usuario buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }
}
