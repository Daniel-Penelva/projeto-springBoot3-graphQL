package com.daniel.projetographql.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    private String id;

    private String nome;

    private double preco;
    private int quantidade;

    // Muitos produtos para uma categoria - é uma relação bidirecional com categoria
    @ManyToOne
    private Categoria categoria;
    
}
