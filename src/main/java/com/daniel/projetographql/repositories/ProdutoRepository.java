package com.daniel.projetographql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.projetographql.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{
    
}
