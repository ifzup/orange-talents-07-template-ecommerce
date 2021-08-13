package br.com.zupacademy.iagofaria.mercadolivre.produto;


import br.com.zupacademy.iagofaria.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.iagofaria.mercadolivre.security.AuthenticateService;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    private UploaderFake uploaderFake;

    private AuthenticateService authenticateService;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, UploaderFake uploaderFake, AuthenticateService authenticateService) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.uploaderFake = uploaderFake;
        this.authenticateService = authenticateService;
    }

    @PostMapping
    public ResponseEntity<ProdutoRequest> cadastraProduto(@RequestBody @Valid ProdutoRequest produto, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoModel = produto.converter(categoriaRepository, usuario);
        if (produtoModel.isPresent()) {
            produtoRepository.save(produtoModel.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(@PathVariable Long id, @Valid ImagemProdutoRequest imagemForm, @AuthenticationPrincipal Usuario usuario) {

        Optional<Produto> produtoObj = produtoRepository.findById(id);
        if (produtoObj.isPresent()) {
            Produto produto = produtoObj.get();
            if (!produto.pertenceAoUsuario(usuario.getEmail())) return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Você não pode adicionar fotos a um produto que não é seu");

            Set<String> links = uploaderFake.envia(imagemForm.getImagens());
            produto.associaImagens(links);
            produtoRepository.save(produto);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();

    }
}
