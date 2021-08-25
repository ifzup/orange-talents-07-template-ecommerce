package br.com.zupacademy.iagofaria.mercadolivre.compras;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
}
