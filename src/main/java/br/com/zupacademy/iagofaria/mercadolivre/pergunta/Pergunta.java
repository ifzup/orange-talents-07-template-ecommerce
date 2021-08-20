package br.com.zupacademy.iagofaria.mercadolivre.pergunta;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private final String titulo;

    @ManyToOne
    @NotNull
    @Valid
    private Usuario interessada;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    public Pergunta(@NotBlank String titulo,
                    @NotNull @Valid Usuario interessada,
                    @NotNull @Valid Produto produto) {
        this.titulo = titulo;
        this.interessada = interessada;
        this.produto = produto;
    }
}
