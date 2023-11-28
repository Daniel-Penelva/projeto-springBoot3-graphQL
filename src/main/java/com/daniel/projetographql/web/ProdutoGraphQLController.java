package com.daniel.projetographql.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.daniel.projetographql.dtos.ProdutoRequest;
import com.daniel.projetographql.entities.Categoria;
import com.daniel.projetographql.entities.Produto;
import com.daniel.projetographql.repositories.CategoriaRepository;
import com.daniel.projetographql.repositories.ProdutoRepository;

@Controller
public class ProdutoGraphQLController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar produto
    @QueryMapping
    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
    
    // Buscar produto por id
    @QueryMapping
    public Produto listarProdutoPorId(@Argument String id){
        return produtoRepository.findById(id).orElseThrow(
            () -> new RuntimeException(String.format("Produto %s não encontrado", id)));
    }

    // Listar categoria
    @QueryMapping
    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    // Buscar categoria por id
    @QueryMapping
    public Categoria listarCategoriaPorId(@Argument Long id){
        return categoriaRepository.findById(id).orElseThrow(
            () -> new RuntimeException(String.format("Categoria %s não encontrado", id)));
    }

    // Criar produto
    @MutationMapping
    public Produto criarProduto(@Argument ProdutoRequest produtoRequest){
       Categoria categoria = categoriaRepository.findById(produtoRequest.categoriaId()).orElse(null);

       var produto = new Produto();
       produto.setId(UUID.randomUUID().toString());
       produto.setNome(produtoRequest.nome());
       produto.setPreco(produtoRequest.preco());
       produto.setQuantidade(produtoRequest.quantidade());
       produto.setCategoria(categoria);

       return produtoRepository.save(produto);
    }

    // Atualizar Produto por id
    @MutationMapping
    public Produto atualizarProduto(@Argument String id, @Argument ProdutoRequest produtoRequest){
       Categoria categoria = categoriaRepository.findById(produtoRequest.categoriaId()).orElse(null);

       var produto = new Produto();
       produto.setId(id);
       produto.setNome(produtoRequest.nome());
       produto.setPreco(produtoRequest.preco());
       produto.setQuantidade(produtoRequest.quantidade());
       produto.setCategoria(categoria);

       return produtoRepository.save(produto);
    }

    // Deletar Produto por id
    @MutationMapping
    public void deletarProduto(@Argument String id){
       produtoRepository.deleteById(id);
    }
}
