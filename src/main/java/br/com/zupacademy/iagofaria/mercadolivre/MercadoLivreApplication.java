package br.com.zupacademy.iagofaria.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class MercadoLivreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercadoLivreApplication.class, args);
    }

}
