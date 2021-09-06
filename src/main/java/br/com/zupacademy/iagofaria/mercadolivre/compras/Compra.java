package br.com.zupacademy.iagofaria.mercadolivre.compras;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @Positive
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;

    @Enumerated
    @NotNull
    private GatewayPagamento gatewayPagamento;


    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Pagamento> tentativasPagamento = new HashSet<>();


    public Compra(@NotNull @Valid Produto produto, @Positive int quantidade,
                  @NotNull @Valid Usuario comprador, GatewayPagamento gatewayPagamento) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gatewayPagamento = gatewayPagamento;
    }

    public String geraUrlGateway(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayPagamento.criaUrl(this, uriComponentsBuilder);
    }

    @Deprecated
    public Compra() {
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Set<Pagamento> getTentativasPagamento() {
        return tentativasPagamento;
    }


    public void adicionaPagamento(@Valid GatewayPagamentoRequest request) {
        Pagamento novaTransacao = request.toPagamento(this);

        //1
        Assert.state(!this.tentativasPagamento.contains(novaTransacao),
                "Já existe um pagamento semelhante em processamento" + novaTransacao);
        //1
        Assert.state(transacoesConcluidasComSucesso().isEmpty(),"Compra já concluída");

        this.tentativasPagamento.add(novaTransacao);
    }
    private Set<Pagamento> transacoesConcluidasComSucesso() {
        Set<Pagamento> transacoesConcluidasComSucesso = this.tentativasPagamento.stream()
                .filter(Pagamento::concluidoComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Já existe pagamentos concluidos com sucesso nesta compra "+this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public Usuario getDonoProduto(){ return produto.getDono();}

}
