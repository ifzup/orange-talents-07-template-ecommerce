package br.com.zupacademy.iagofaria.mercadolivre.compras;

import javax.validation.constraints.NotNull;

public class RankingRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDonoProduto;

    public RankingRequest(Long idCompra, Long idDonoProduto) {
        super();
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "NovaCompraNFRequest [idCompra=" + idCompra + ", idComprador="
                + idDonoProduto + "]";
    }
}
