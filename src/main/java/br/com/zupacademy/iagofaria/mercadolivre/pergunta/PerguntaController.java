package br.com.zupacademy.iagofaria.mercadolivre.pergunta;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "/produtos/{id}/perguntas")
    @Transactional
    public String cria(@RequestBody @Valid PerguntaRequest perguntaForm, @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class, id);

        Usuario interessada = usuarioRepository.findByEmail("email@email.com").get();
        Pergunta novaPergunta = perguntaForm.toModel(interessada, produto);
        manager.persist(novaPergunta);
        return "Pergunta adicionada com sucesso";
    }
}
