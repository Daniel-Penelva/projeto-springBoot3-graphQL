package com.daniel.projetographql.dtos;

public record ProdutoRequest(String id, String nome, double preco, int quantidade, Long categoriaId) {
    
}
