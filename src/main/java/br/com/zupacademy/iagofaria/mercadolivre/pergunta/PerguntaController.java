package br.com.zupacademy.iagofaria.mercadolivre.pergunta;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import static br.com.zupacademy.iagofaria.mercadolivre.produto.Produto.buscaPorId;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DisparadorDeEmail disparadorDeEmail;


    /*@PostMapping(value = "/produtos/{id}/perguntas")
    @Transactional
    public void criaPergunta(@RequestBody @Valid PerguntaRequest perguntaForm, @PathVariable("id") Long id), @AuthenticationPrincipal Usuario interessada {
        //Produto produto = manager.find(Produto.class, id);
        Produto produto = Produto.buscaPorId();
        Usuario vendedor = produto.getUsuario();
        //Usuario interessada = usuarioRepository.findByEmail("email@email.com").get();
        Pergunta novaPergunta = perguntaForm.toModel(vendedor, interessada, produto);
        manager.persist(novaPergunta);
        //return "Pergunta adicionada com sucesso";
    }*/
    @PostMapping("/{id}/pergunta")
    @Transactional
    public void enviarPergunta(@PathVariable("id") Long id,
                               @RequestBody @Valid PerguntaRequest request,
                               @AuthenticationPrincipal Usuario usuarioAutor) {
        Produto produto = buscaPorId(manager, id);
        Usuario usuarioVendedor = produto.getUsuario();

        Pergunta pergunta = request.toModel(usuarioAutor, usuarioVendedor, produto);

        manager.persist(pergunta);

        Email email = new Email(
                id,
                "nao-responda@mercadolivre.com.br",
                usuarioVendedor.getUsername(),
                pergunta.toEmail()
        );

        disparadorDeEmail.enviar(email);
    }
}
