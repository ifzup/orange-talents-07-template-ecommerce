package br.com.zupacademy.iagofaria.mercadolivre.compras;

import br.com.zupacademy.iagofaria.mercadolivre.pergunta.Email;
import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    private Email email;
    @PostMapping()
    @Transactional
    public ResponseEntity<?> finalizaCompra(@RequestBody @Valid CompraRequest compraRequest,
                                            @PathVariable("id") Long id,
                                            @AuthenticationPrincipal Usuario usuario,
                                            UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produto = produtoRepository.findById(compraRequest.getIdProduto()).get();

        boolean estoque = produto.atualizaEstoque(compraRequest.getQuantidade());
        if (estoque) {

            Compra compra = compraRequest.toModel(produto, usuario);
            compraRepository.save(compra);

            var response = new HashMap<>();
            response.put("Url do pagamento", compra.geraUrlGateway(uriComponentsBuilder));
            return ResponseEntity.ok(response);
        }


        BindException problemaComEstoque = new BindException(compraRequest,
                "CompraRequest");
        problemaComEstoque.reject(null,
                "Não foi possível realizar a compra por conta do estoque");
        throw problemaComEstoque;

    }
}
