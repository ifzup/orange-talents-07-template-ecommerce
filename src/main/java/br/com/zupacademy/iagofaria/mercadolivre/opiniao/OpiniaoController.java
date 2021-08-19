package br.com.zupacademy.iagofaria.mercadolivre.opiniao;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    private ProdutoRepository produtoRepository;

    public OpiniaoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping(value = "/produtos/{id}/opiniao")
    @Transactional
    public ResponseEntity<OpiniaoResponse> adiciona(@RequestBody @Valid OpiniaoRequest opiniaoRequest,
                                                    @PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuario ) {

        Optional<Produto> produto = produtoRepository.findById(id);
        Opiniao avaliacao = opiniaoRequest.toModel(produto, usuario);
        manager.persist(avaliacao);
        try {
            return ResponseEntity.ok().body(new OpiniaoResponse(avaliacao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
