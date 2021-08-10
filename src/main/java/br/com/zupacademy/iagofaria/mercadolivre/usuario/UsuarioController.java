package br.com.zupacademy.iagofaria.mercadolivre.usuario;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid UsuarioRequest usuarioForm){
        Usuario usuario = usuarioForm.criaUsuario();
        manager.persist((usuario));
    }
}
