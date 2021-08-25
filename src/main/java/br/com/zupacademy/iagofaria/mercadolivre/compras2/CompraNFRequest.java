package br.com.zupacademy.iagofaria.mercadolivre.compras2;

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

    @Override
    public String toString() {
        return "NovaCompraNFRequest [idCompra=" + idCompra + ", idComprador="
                + idComprador + "]";
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}
