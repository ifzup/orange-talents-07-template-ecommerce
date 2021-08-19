package br.com.zupacademy.iagofaria.mercadolivre.opiniao;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Optional;

public class OpiniaoRequest {

    @Min(1) @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo,
                              @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Optional<Produto> produto, @NotNull @Valid Usuario consumidor) {
        return new Opiniao(this.nota, this.titulo, this.descricao,produto.get(),consumidor);
    }
}
