package br.com.zupacademy.iagofaria.mercadolivre.usuario;

import br.com.zupacademy.iagofaria.mercadolivre.validator.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario criaUsuario() {
        return new Usuario(this.email, new SenhaLimpa(senha));
    }
}
