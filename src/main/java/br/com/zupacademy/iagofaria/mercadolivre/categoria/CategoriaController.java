package br.com.zupacademy.iagofaria.mercadolivre.categoria;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CategoriaRequest CategoriaForm){
        Categoria categoria = CategoriaForm.converter(manager);
        manager.persist(categoria);
    }

}
