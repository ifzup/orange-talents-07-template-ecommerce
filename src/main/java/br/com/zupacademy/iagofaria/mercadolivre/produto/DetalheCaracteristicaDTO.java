package br.com.zupacademy.iagofaria.mercadolivre.produto;

public class DetalheCaracteristicaDTO {

    private String descricao;
    private String nome;

    public DetalheCaracteristicaDTO(Caracteristica caracteristica){
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }
}
