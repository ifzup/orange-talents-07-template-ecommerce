package br.com.zupacademy.iagofaria.mercadolivre.compras;

import javax.validation.constraints.NotNull;

public class CompraNFRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public CompraNFRequest(Long idCompra, Long idComprador) {
        super();
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}
