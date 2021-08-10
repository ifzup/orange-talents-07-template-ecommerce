package br.com.zupacademy.iagofaria.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SenhaLimpa {
    @NotBlank
    @Min(value = 6)
    private String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
