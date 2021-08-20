package br.com.zupacademy.iagofaria.mercadolivre.produto;

import br.com.zupacademy.iagofaria.mercadolivre.categoria.Categoria;
import br.com.zupacademy.iagofaria.mercadolivre.opiniao.Opiniao;
import br.com.zupacademy.iagofaria.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;
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
import java.util.function.Function;
import java.util.stream.Collectors;

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
    //@Size(max = 1000)
    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
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


    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    private Set<Pergunta> perguntas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opiniao = new HashSet<>();


    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(String email) {
        return this.usuario.getEmail().equals(email);
    }

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
    public static Produto buscaPorId(EntityManager manager, Long id){
        Produto produto = manager.find(Produto.class, id);
        if(produto == null){
            throw new ApiException(HttpStatus.NOT_FOUND, "Produdo nao encontrado");
        }
        return produto;
    }
    public Usuario getUsuario(){
        return this.usuario;
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

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<Caracteristica> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public Instant getMomentoCadastro() {
        return momentoCadastro;
    }
    public Set<DetalheCaracteristicaDTO> mapCaracteristicas(
            Function<Caracteristica, DetalheCaracteristicaDTO> fMapeadora) {

        return this.listaCaracteristicas
                .stream()
                .map(fMapeadora)
                .collect(Collectors.toSet());

    }

    public Set<String> mapImagens(Function<ImagemProduto, String> fMapeadora) {
        return this.imagens
                .stream()
                .map(fMapeadora)
                .collect(Collectors.toSet());

    }

    public Set<String> mapPerguntas(Function<Pergunta, String> fMapeadora) {
        return this.perguntas
                .stream()
                .map(fMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapOpinioes(Function<Opiniao, T> fMapeadora) {
        return this.opiniao
                .stream()
                .map(fMapeadora)
                .collect(Collectors.toSet());
    }
}
