package br.com.zupacademy.iagofaria.mercadolivre.compras;

public interface GatewayPagamentoRequest {

    Pagamento toPagamento(Compra compra);
}
