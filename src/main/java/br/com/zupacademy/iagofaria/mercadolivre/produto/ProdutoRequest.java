package br.com.zupacademy.iagofaria.mercadolivre.produto;

import br.com.zupacademy.iagofaria.mercadolivre.categoria.Categoria;
import br.com.zupacademy.iagofaria.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import br.com.zupacademy.iagofaria.mercadolivre.validator.FindObjectId;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    @JsonProperty(value = "nome")
    private String nome;

    @Positive
    @NotNull
    @JsonProperty(value = "valor")
    private BigDecimal valor;

    @Positive
    @NotNull
    @JsonProperty(value = "quantidade")
    private Integer quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
    @JsonProperty(value = "descricao")
    private String descricao;

    @NotNull
    @FindObjectId(domainClass = Categoria.class, fieldName="id")
    @JsonProperty(value="idCategoria")
    private Long idCategoria;

    @JsonProperty(value = "caracteristicas")
    @Size(min = 3, message = "Cadastre no mínimo 3 características")
    @Valid
    private final Set<CaracteristicasRequest> listaCaracteristicas;

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade,
                          Set<CaracteristicasRequest> listaCaracteristicas,
                          String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidade;
        this.listaCaracteristicas = listaCaracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria ;
    }

    public Optional<Produto> converter(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoriaObj = categoriaRepository.findById(idCategoria);
        Categoria categoria = categoriaObj.get();

        Produto produto = new Produto(nome, valor, quantidadeDisponivel, descricao,
                categoria, usuario, listaCaracteristicas);

        return Optional.of(produto);
    }
}
