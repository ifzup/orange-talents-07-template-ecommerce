package br.com.zupacademy.iagofaria.mercadolivre.compras;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @Positive
    private int quantidade;
    @NotNull
    private Long idProduto;
    @NotNull
    private GatewayPagamento gateway;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CompraRequest(@Positive int quantidade,
                             @NotNull Long idProduto, GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Compra toModel(Produto produto, Usuario comprador) {
        return new Compra(produto, this.quantidade, comprador, this.gateway);
    }

}
