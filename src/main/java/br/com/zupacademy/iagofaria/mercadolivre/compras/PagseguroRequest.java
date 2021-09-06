package br.com.zupacademy.iagofaria.mercadolivre.compras;

import javax.validation.constraints.NotBlank;

public class PagseguroRequest implements GatewayPagamentoRequest{

    @NotBlank
    private String gatewayId;
    @NotBlank
    private String status;

    public PagseguroRequest(String gatewayId, String status) {
        this.gatewayId = gatewayId;
        this.status = status;
    }

    @Override
    public Pagamento toPagamento(Compra compra) {
        return new Pagamento(compra, gatewayId, toStatus());
    }

    private PagamentoStatus toStatus() {
        if(status.equalsIgnoreCase("SUCESSO"))
            return PagamentoStatus.SUCESSO;
        return PagamentoStatus.ERRO;
    }
}
