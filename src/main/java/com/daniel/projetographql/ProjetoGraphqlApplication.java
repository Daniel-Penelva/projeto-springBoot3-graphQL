package com.daniel.projetographql;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.daniel.projetographql.entities.Categoria;
import com.daniel.projetographql.entities.Produto;
import com.daniel.projetographql.repositories.CategoriaRepository;
import com.daniel.projetographql.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoGraphqlApplication.class, args);

	}

	// CommandLineRunner executar ações de inicialização ou tarefas específicas quando um aplicativo Spring Boot é inicializado.
	// http://localhost:8090/h2-console
	@Bean
	CommandLineRunner commandLineRunner(CategoriaRepository categoriaRepository, ProdutoRepository productoRepository){
		Random random = new Random();
		return args -> {
			List.of("Computadores","Impresoras","Smartphones").forEach(cat -> {
				Categoria categoria = Categoria.builder().nome(cat).build();
				categoriaRepository.save(categoria);
			});

			categoriaRepository.findAll().forEach(categoria -> {
				for(int i = 0;i < 10; i++){
					Produto produto = Produto.builder()
							.id(UUID.randomUUID().toString())
							.nome("Computador "+i)
							.preco(100 + Math.random()*50000)
							.quantidade(random.nextInt(100))
							.categoria(categoria)
							.build();
					productoRepository.save(produto);
				}
			});
		};
	}
}
