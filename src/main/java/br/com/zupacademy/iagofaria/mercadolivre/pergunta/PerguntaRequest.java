package br.com.zupacademy.iagofaria.mercadolivre.pergunta;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @Override
    public String toString() {
        return "PerguntaRequest{" + "titulo='" + titulo + '\'' + '}';
    }

    public PerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(@NotNull @Valid Usuario interessada, @NotNull @Valid Produto produto) {
        return new Pergunta(titulo, interessada, produto);
    }
}
