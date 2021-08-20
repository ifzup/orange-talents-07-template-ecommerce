package br.com.zupacademy.iagofaria.mercadolivre.pergunta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Email {
    String remetente;
    String destinatario;
    String corpo;

    @JsonFormat(pattern = "dd/MM/aaaa")
    LocalDateTime instanteDeEnvio;

    Long idProduto;

    public Email(Long idProduto,
                 String remetente,
                 String destinatario,
                 String corpo
    ) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.instanteDeEnvio = LocalDateTime.now();
        this.idProduto = idProduto;
    }

    @Override
    public String toString() {
        return "Email{" +
                "remetente='" + remetente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", corpo='" + corpo + '\'' +
                ", instanteDeEnvio=" + instanteDeEnvio +
                ", idProduto=" + idProduto +
                '}';
    }
}
