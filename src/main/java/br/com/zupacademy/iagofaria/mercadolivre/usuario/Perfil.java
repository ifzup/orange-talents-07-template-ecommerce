package br.com.zupacademy.iagofaria.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;

public class Perfil implements GrantedAuthority {
    private Long id;

    private String nome;

    public Perfil(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
