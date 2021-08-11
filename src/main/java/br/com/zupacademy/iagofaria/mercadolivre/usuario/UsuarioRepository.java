package br.com.zupacademy.iagofaria.mercadolivre.usuario;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository {
    Optional<Usuario> findByEmail(String email);
}
