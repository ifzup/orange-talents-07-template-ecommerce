package br.com.zupacademy.iagofaria.mercadolivre.categoria;

import br.com.zupacademy.iagofaria.mercadolivre.validator.FindObjectId;
import br.com.zupacademy.iagofaria.mercadolivre.validator.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @FindObjectId(domainClass = Categoria.class, fieldName = "id")
    private Long idDaCategoriaMae;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdDaCategoriaMae(Long idDaCategoriaMae) {
        this.idDaCategoriaMae = idDaCategoriaMae;
    }


    public Categoria converter(EntityManager manager) {
        Categoria categoria = new Categoria(nome);
        if (idDaCategoriaMae != null) {
            Categoria categoriaMae = manager.find(Categoria.class, idDaCategoriaMae);
            categoriaMae.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
