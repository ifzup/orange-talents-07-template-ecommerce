package br.com.zupacademy.iagofaria.mercadolivre.security;

public class LoginRequest {

    private String username;
    private String senha;

    public void setUsername(String username){
        this.username = username;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getUsername(){
        return username;
    }

    public String getSenha(){
        return senha;
    }
}