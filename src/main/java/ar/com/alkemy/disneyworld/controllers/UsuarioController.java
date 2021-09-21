package ar.com.alkemy.disneyworld.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.alkemy.disneyworld.entities.Usuario;
import ar.com.alkemy.disneyworld.models.request.LoginRequest;
import ar.com.alkemy.disneyworld.models.response.GenericResponse;
import ar.com.alkemy.disneyworld.models.response.LoginResponse;
import ar.com.alkemy.disneyworld.services.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("/auth/register")
    public ResponseEntity<GenericResponse> register(@RequestBody Usuario usuario) {

        GenericResponse r = new GenericResponse();

        if (service.validarDatosUsuario(usuario)) {

            Usuario u = service.registrar(usuario.getFullName(), usuario.getUsername(), usuario.getPassword(),
                    usuario.getEmail());

            r.isOk = true;
            r.id = u.getUsuarioId();
            r.message = "¡Usuario registrado con exito!";

            return ResponseEntity.ok(r);

        } else {

            r.isOk = false;
            r.message = "¡Lo siento ese usuario|email ya se encuentra registrado!";

            return ResponseEntity.badRequest().body(r);

        }

    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) throws Exception {

        Usuario u = service.login(req.username, req.password);
        String token = getJWTToken(u.getUsername());

        LoginResponse r = new LoginResponse();
        r.id = u.getUsuarioId();
        r.token = token;
        r.username = u.getUsername();

        return ResponseEntity.ok(r);

    }

    private String getJWTToken(String username) {

        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts.builder().setId("softtekJWT").setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
