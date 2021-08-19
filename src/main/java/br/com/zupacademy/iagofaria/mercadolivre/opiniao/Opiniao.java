package br.com.zupacademy.iagofaria.mercadolivre.opiniao;

import br.com.zupacademy.iagofaria.mercadolivre.produto.Produto;
import br.com.zupacademy.iagofaria.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1) @Max(5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 500)
    private String descricao;

    @ManyToOne
    @NotNull
    @Valid
    private Produto produto;

    @ManyToOne
    private @NotNull @Valid Usuario consumidor;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo,
                   @NotBlank @Size(max = 500) String descricao,
                   @NotNull @Valid Produto produto,
                   @NotNull @Valid Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((consumidor == null) ? 0 : consumidor.hashCode());
        result = prime * result
                + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((produto == null) ? 0 : produto.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Opiniao other = (Opiniao) obj;
        if (consumidor == null) {
            if (other.consumidor != null)
                return false;
        } else if (!consumidor.equals(other.consumidor))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (produto == null) {
            if (other.produto != null)
                return false;
        } else if (!produto.equals(other.produto))
            return false;
        if (titulo == null) {
            return other.titulo == null;
        } else return titulo.equals(other.titulo);
    }
}
