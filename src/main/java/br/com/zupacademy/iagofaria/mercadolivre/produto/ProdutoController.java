package br.com.zupacademy.iagofaria.mercadolivre.produto;


import br.com.zupacademy.iagofaria.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<ProdutoRequest> cadastraProduto(@RequestBody @Valid ProdutoRequest produto, @AuthenticationPrincipal Usuario usuario){
        Optional<Produto> produtoModel = produto.converter(categoriaRepository, usuario);
        if(produtoModel.isPresent()) {
            produtoRepository.save(produtoModel.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
