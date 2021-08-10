package br.com.zupacademy.iagofaria.mercadolivre.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataCadastro  = LocalDateTime.now();

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public Usuario(@NotBlank @Email String email, SenhaLimpa senha) {
        this.email = email;
        this.senha = senha.hash();
    }

    @Deprecated
    public Usuario() {
    }

    //teste do commit
}
