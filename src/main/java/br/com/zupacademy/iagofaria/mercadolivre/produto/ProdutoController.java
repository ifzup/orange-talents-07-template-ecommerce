package br.com.zupacademy.iagofaria.mercadolivre.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid ProdutoRequest produtoForm){
        Produto produto = produtoForm.criarProduto();
        produtoRepository.save(produto);

    }
}
