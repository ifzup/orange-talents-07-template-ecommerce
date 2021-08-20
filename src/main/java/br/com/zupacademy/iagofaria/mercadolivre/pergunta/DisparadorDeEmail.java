package br.com.zupacademy.iagofaria.mercadolivre.pergunta;

import org.springframework.stereotype.Component;

@Component
public class DisparadorDeEmail {
    public String enviar(Email email) {
        System.out.println(email.toString());
        return email.toString();

    }
}
