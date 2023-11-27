package com.daniel.projetographql.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.daniel.projetographql.entities.Produto;
import com.daniel.projetographql.repositories.CategoriaRepository;
import com.daniel.projetographql.repositories.ProdutoRepository;

@Controller
public class ProdutoGraphQLController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @QueryMapping
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
    
    public Produto listarProdutoPorId(@Argument String id){
        return produtoRepository.findById(id).orElseThrow(
            () -> new RuntimeException(String.format("Produto %s não encontrado", id)));
    }
}
