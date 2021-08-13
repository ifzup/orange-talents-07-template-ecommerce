package br.com.zupacademy.iagofaria.mercadolivre.produto;

import javax.persistence.*;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private String nome;

    private String descricao;

    @Deprecated
    public Caracteristica() {
    }

    public Caracteristica(String nome, String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }
}
