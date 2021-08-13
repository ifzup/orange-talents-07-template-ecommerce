package br.com.zupacademy.iagofaria.mercadolivre.produto;

import br.com.zupacademy.iagofaria.mercadolivre.categoria.Categoria;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Positive
    @NotNull
    private BigDecimal valor;

    @Positive
    @NotNull
    private Integer quantidadeDisponivel;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @OneToMany//(mappedBy = "produto", cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private Set<Caracteristica> listaCaracteristicas = new HashSet<>();

    private Instant momentoCadastro = Instant.now();

    public Produto(String nome, BigDecimal valor, Integer quantidadeDisponivel, String descricao,
                        Categoria categoria, Usuario usuario, Set<CaracteristicasRequest> listaCaracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;

        listaCaracteristicas.forEach(cr -> this.listaCaracteristicas.add(cr.converter(this)));

        Assert.isTrue(this.listaCaracteristicas.size() >= 3, "Precisa ter no mínimo 3 características");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Deprecated
    public Produto() {
    }
}
