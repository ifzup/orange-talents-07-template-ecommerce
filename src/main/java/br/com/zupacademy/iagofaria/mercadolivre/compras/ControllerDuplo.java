package br.com.zupacademy.iagofaria.mercadolivre.compras;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ControllerDuplo {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody CompraNFRequest request) throws InterruptedException {
        System.out.println("criando nota "+request);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingRequest request) throws InterruptedException {
        System.out.println("criando ranking"+request);
        Thread.sleep(150);
    }
}
