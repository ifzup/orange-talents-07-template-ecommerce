package br.com.zupacademy.iagofaria.mercadolivre.security;


import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Deprecated
    public AuthenticateController() {
    }

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest request) {
        String token;

        Optional<Usuario> possivelUsuarioCadastrado = userRepository.findByEmail(request.getUsername());

        if (possivelUsuarioCadastrado.get() == null) {
            throw new UsernameNotFoundException("Credenciais inválidas");
        }

        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getSenha());

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            token = tokenService.gerarToken(authenticate);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new TokenResponse(token, "BEARER"));
    }
}
