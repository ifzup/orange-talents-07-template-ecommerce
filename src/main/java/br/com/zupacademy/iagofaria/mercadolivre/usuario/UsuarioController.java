package br.com.zupacademy.iagofaria.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository userRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioRequest usuarioForm) {
        Usuario usuario = usuarioForm.criaUsuario();
        userRepository.save(usuario);
    }
}
