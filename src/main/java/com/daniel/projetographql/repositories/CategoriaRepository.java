package com.daniel.projetographql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.projetographql.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
