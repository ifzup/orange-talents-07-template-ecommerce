package br.com.zupacademy.iagofaria.mercadolivre.produto;

import br.com.zupacademy.iagofaria.mercadolivre.opiniao.Opiniao;
import br.com.zupacademy.iagofaria.mercadolivre.pergunta.Pergunta;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.IntStream;

public class DetalhesDoProduto {

    private String nome;
    private String descricao;
    private BigDecimal valor;

    @JsonView
    private Set<DetalheCaracteristicaDTO> caracteristicas;
    private Set<String> linksImagens;
    private Set<String> perguntas;
    private Set<Map<String, String>> avaliacoes;
    private double mediaNota;

   /* public DetalhesDoProduto(String nome,
                             String descricao,
                             BigDecimal valor,
                             Set<DetalheCaracteristicaDTO> caracteristicas,
                             Set<String> linksImagens,
                             Set<String> perguntas,
                             Set<Map<String, String>> avaliacoes,
                             double meediaNota) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.caracteristicas = caracteristicas;
        this.linksImagens = linksImagens;
        this.perguntas = perguntas;
        this.avaliacoes = avaliacoes;
        this.meediaNota = meediaNota;
    }*/
    public DetalhesDoProduto(Produto produto){
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristicaDTO::new);
        this.linksImagens = produto.mapImagens(ImagemProduto::getLink);
        this.perguntas = produto.mapPerguntas(Pergunta::getTitulo);

        this.avaliacoes = produto.mapOpinioes(opiniao ->
                Map.of("titulo", opiniao.getTitulo(),
                        "descricao", opiniao.getDescricao()));

        Set<Integer> notas = produto.mapOpinioes(Opiniao::getNota);
        IntStream notasToInt = notas.stream().mapToInt(nota -> nota);
        OptionalDouble media = notasToInt.average();
        if(media.isPresent()) {
            this.mediaNota = media.getAsDouble();
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public double getMediaNota() {
        return mediaNota;
    }
    public void setCaracteristicas(Set<DetalheCaracteristicaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<DetalheCaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public Set<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getAvaliacoes() {
        return avaliacoes;
    }
}
