# GraphQL

## O que é GraphQL?

**GraphQL** é uma linguagem de consulta para APIs (Interface de Programação de Aplicações) e também é um mecanismo de execução dessas consultas com seus tipos para o servidor. Foi desenvolvido pelo Facebook e lançado como uma especificação aberta em 2015. Desde então, foi adotado por muitas organizações e comunidades de desenvolvedores.

Diferentemente das APIs tradicionais, onde as respostas são fixas, o GraphQL permite que os clientes solicitem apenas os dados de que precisam. Ele fornece uma maneira mais eficiente e poderosa de interagir com APIs, permitindo que os clientes especifiquem a estrutura exata dos dados necessários.

## Princípios Básicos

### 1. **Tipos e Campos:**
   - O GraphQL define um esquema que descreve os tipos de dados disponíveis e seus relacionamentos.
   - Os tipos podem ter campos que são resolvidos para retornar dados.

### 2. **Consultas e Mutações:**
   - As consultas são usadas para recuperar dados do servidor.
   - Mutações são usadas para modificar dados no servidor.

### 3. **Sistema de Tipos:**
   - Cada campo tem um tipo específico, e as consultas devem seguir um tipo definido no esquema.

### 4. **Resolução:**
   - Os campos do esquema são resolvidos por funções no servidor que determinam como recuperar ou salvar os dados.

## Estrutura de uma Consulta GraphQL

Uma consulta GraphQL básica pode se parecer com o seguinte:

```graphql
query {
  user(id: 1) {
    id
    name
    email
  }
}
```

Nesta consulta, está sendo solicitado os campos `id`, `name` e `email` do usuário com `id` igual a 1.

## Exemplo de Mutação GraphQL

Uma mutação para adicionar um novo usuário pode ser assim:

```graphql
mutation {
  createUser(input: { name: "John Doe", email: "john@example.com" }) {
    id
    name
    email
  }
}
```

## Ferramentas e Clientes GraphQL

Existem várias ferramentas e clientes GraphQL disponíveis para facilitar o desenvolvimento e teste de consultas, como o [GraphiQL](https://github.com/graphql/graphiql) para ambientes de desenvolvimento.

## Integração com Spring Boot

No ecossistema Java, o Spring Boot oferece suporte ao GraphQL. A integração pode ser feita usando a biblioteca [graphql-java](https://github.com/graphql-java/graphql-java) ou [graphql-spring-boot](https://github.com/graphql-java-kickstart/graphql-spring-boot).

## Recursos Adicionais

- [Site Oficial do GraphQL](https://graphql.org/): Contém a especificação oficial, tutoriais e guias.
- [GraphQL Java Kickstart](https://github.com/graphql-java-kickstart): Uma biblioteca para facilitar a implementação do GraphQL em aplicativos Java.

# Classe Produto

## Visão Geral

A classe `Produto` é uma entidade que representa um item em um sistema de gerenciamento de produtos. Ela é mapeada para uma tabela no banco de dados por meio da anotação `@Entity` do Jakarta Persistence API (JPA). A classe utiliza a biblioteca Lombok para gerar automaticamente métodos como getters, setters, construtores e outros, reduzindo a necessidade de código boilerplate.

```java
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
```

## Atributos

### 1. `id` (String)

- **Descrição:** Identificador único do produto.
- **Anotação:** `@Id` indica que este campo é a chave primária da entidade.

### 2. `nome` (String)

- **Descrição:** Nome do produto.
- **Tipo:** String.
- **Anotação:** Não há anotações adicionais.

### 3. `preco` (double)

- **Descrição:** Preço do produto.
- **Tipo:** Double.
- **Anotação:** Não há anotações adicionais.

### 4. `quantidade` (int)

- **Descrição:** Quantidade disponível do produto.
- **Tipo:** Inteiro.
- **Anotação:** Não há anotações adicionais.

### 5. `categoria` (Categoria)

- **Descrição:** Categoria à qual o produto pertence.
- **Tipo:** Categoria (outra entidade).
- **Anotação:** `@ManyToOne` indica uma relação muitos-para-um com a entidade Categoria, estabelecendo uma relação bidirecional.

## Relacionamento Bidirecional

A anotação `@ManyToOne` estabelece uma relação bidirecional entre `Produto` e `Categoria`. Isso significa que, além de cada produto ter uma referência para a categoria à qual pertence, a entidade `Categoria` também terá uma lista de produtos associados.

## Construtores

- **Construtor Padrão:** Criado automaticamente pelo Lombok para inicialização sem argumentos.
- **Construtor Completo:** Criado automaticamente pelo Lombok para inicialização com todos os argumentos.
- **Construtor Builder:** Criado automaticamente pelo Lombok para inicialização usando o padrão Builder, permitindo uma criação mais fluente de instâncias.

## Métodos Getters e Setters

- **Getters:** Métodos gerados automaticamente pelo Lombok para recuperar os valores dos atributos.
- **Setters:** Métodos gerados automaticamente pelo Lombok para atribuir valores aos atributos.

# Classe Categoria

## Visão Geral

A classe `Categoria` é uma entidade que representa uma categoria de produtos em um sistema de gerenciamento. Ela é mapeada para uma tabela no banco de dados usando as anotações do Jakarta Persistence API (JPA). A classe utiliza a biblioteca Lombok para gerar automaticamente métodos como getters, setters, construtores e outros, reduzindo a necessidade de código boilerplate.

```java
package com.daniel.projetographql.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //  Uma categoria para muitos produtos - é uma relação bidirecional comm produtos
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

}
```

## Atributos

### 1. `id` (Long)

- **Descrição:** Identificador único da categoria.
- **Anotações:** 
  - `@Id`: Indica que este campo é a chave primária da entidade.
  - `@GeneratedValue`: Especifica que o valor do campo será gerado automaticamente pelo banco de dados.

### 2. `nome` (String)

- **Descrição:** Nome da categoria.
- **Tipo:** String.
- **Anotação:** Não há anotações adicionais.

### 3. `produtos` (List<Produto>)

- **Descrição:** Lista de produtos associados a esta categoria.
- **Tipo:** Lista de objetos da classe `Produto`.
- **Anotação:** `@OneToMany` indica uma relação um-para-muitos com a entidade `Produto`, estabelecendo uma relação bidirecional. A anotação `mappedBy = "categoria"` indica que o campo `categoria` na classe `Produto` é responsável pelo mapeamento desta relação.

## Relacionamento Bidirecional

A anotação `@OneToMany` estabelece uma relação bidirecional entre `Categoria` e `Produto`. Isso significa que, além de cada categoria ter uma lista de produtos associados, a entidade `Produto` também terá uma referência à categoria à qual pertence.

## Construtores

- **Construtor Padrão:** Criado automaticamente pelo Lombok para inicialização sem argumentos.
- **Construtor Completo:** Criado automaticamente pelo Lombok para inicialização com todos os argumentos.
- **Construtor Builder:** Criado automaticamente pelo Lombok para inicialização usando o padrão Builder, permitindo uma criação mais fluente de instâncias.

## Métodos Getters e Setters

- **Getters:** Métodos gerados automaticamente pelo Lombok para recuperar os valores dos atributos.
- **Setters:** Métodos gerados automaticamente pelo Lombok para atribuir valores aos atributos.

# Repositórios

## Introdução

Os repositórios `CategoriaRepository` e `ProdutoRepository` são interfaces que estendem a interface `JpaRepository` do Spring Data JPA. Essas interfaces são responsáveis por fornecer operações de acesso a dados para as entidades `Categoria` e `Produto` em um sistema de gerenciamento de produtos.

## CategoriaRepository

### Descrição

O `CategoriaRepository` é um repositório para a entidade `Categoria` que fornece métodos para realizar operações com o banco de dados relacionadas a categorias.

```java
package com.daniel.projetographql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.projetographql.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
```

## ProdutoRepository

### Descrição

O `ProdutoRepository` é um repositório para a entidade `Produto` que fornece métodos para realizar operações com o banco de dados relacionadas a produtos.

```java
package com.daniel.projetographql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.projetographql.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{
    
}
```

## Herança da Interface JpaRepository

Ambas as interfaces `CategoriaRepository` e `ProdutoRepository` herdam da interface `JpaRepository` do Spring Data JPA. Esta interface fornece métodos comumente utilizados para operações CRUD (Create, Read, Update, Delete) no banco de dados, evitando a necessidade de escrever código repetitivo.

# Manipulador de Exceções GraphQL

## Introdução

O `GraphQLExceptionHandler` é um componente em um aplicativo Spring Boot que utiliza o Spring GraphQL. Ele estende a classe `DataFetcherExceptionResolverAdapter` do Spring GraphQL, fornecendo uma maneira de lidar com exceções lançadas durante a execução de consultas GraphQL.

```java
package com.daniel.projetographql.exception;

import java.util.List;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return new GraphQLError() {
            @Override
            public String getMessage() {
                return ex.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return null;
            }

            @Override
            public ErrorClassification getErrorType() {
                return null;
            }
        };
    }

}
```

## Funcionalidades Principais

### 1. **`resolveToSingleError`**

- **Descrição:** Método protegido sobrescrito para resolver uma exceção lançada durante a execução da consulta GraphQL.
- **Parâmetros:**
  - `ex` (Throwable): A exceção lançada.
  - `env` (DataFetchingEnvironment): O ambiente de busca de dados GraphQL.
- **Retorno:** Um objeto `GraphQLError` que encapsula a mensagem da exceção.

### 2. **`GraphQLError`**

- **Descrição:** Interface que define um erro GraphQL. Implementada por meio de uma classe anônima no método `resolveToSingleError`.
- **Métodos Principais:**
  - `getMessage`: Retorna a mensagem da exceção como uma String.
  - `getLocations`: Retorna as localizações do erro como uma lista de `SourceLocation` (não implementado).
  - `getErrorType`: Retorna a classificação do erro como um objeto `ErrorClassification` (não implementado).

## Uso Típico

O `GraphQLExceptionHandler` é um componente gerenciado pelo Spring e é invocado automaticamente quando ocorre uma exceção durante a execução de uma consulta GraphQL. Ele encapsula a mensagem da exceção em um objeto `GraphQLError`, fornecendo uma resposta formatada para o cliente GraphQL.

## Conclusão

O `GraphQLExceptionHandler` desempenha um papel crucial na melhoria da experiência do cliente ao lidar com exceções em consultas GraphQL. Ele personaliza a apresentação de erros para os clientes, encapsulando as mensagens de exceção em objetos `GraphQLError` que podem ser processados de maneira adequada pelo cliente GraphQL.

# DTO ProdutoRequest

## Introdução

A classe `ProdutoRequest` é um DTO (Data Transfer Object) que representa os dados necessários para criar ou atualizar um produto em um sistema de gerenciamento de produtos. O uso de DTOs é comum em aplicativos para transferir dados entre camadas, como a camada de controle e a camada de serviço.

```java
package com.daniel.projetographql.dtos;

public record ProdutoRequest(String id, String nome, double preco, int quantidade, Long categoriaId) {
    
}
```

## Características Principais

### 1. **`record`**

- **Descrição:** A classe é declarada como um `record`, uma nova feature introduzida no Java 16. Records são uma forma concisa de declarar classes imutáveis, frequentemente usadas para modelar dados simples.
  
### 2. Atributos

- **Descrição:** A classe possui os seguintes atributos:
  - `id` (String): Identificador único do produto.
  - `nome` (String): Nome do produto.
  - `preco` (double): Preço do produto.
  - `quantidade` (int): Quantidade disponível do produto.
  - `categoriaId` (Long): Identificador único da categoria à qual o produto pertence.

### 3. Construtor

- **Descrição:** O construtor é gerado automaticamente pelo Java para inicializar os atributos do `record`.
  
### 4. Métodos

- **Métodos Getters:** Métodos gerados automaticamente pelo Java para recuperar os valores dos atributos.

## Uso Típico

```java
// Criando uma instância de ProdutoRequest
ProdutoRequest produtoRequest = new ProdutoRequest("123", "Notebook", 1999.99, 50, 1L);

// Acessando os atributos
String id = produtoRequest.id();
String nome = produtoRequest.nome();
double preco = produtoRequest.preco();
int quantidade = produtoRequest.quantidade();
Long categoriaId = produtoRequest.categoriaId();
```

O `ProdutoRequest` é utilizado para transportar dados entre as camadas do aplicativo, permitindo uma representação clara e imutável dos dados necessários para criar ou atualizar um produto. O uso de um `record` simplifica a criação de classes para transferência de dados, eliminando a necessidade de escrever código boilerplate para getters, setters e outros métodos comuns em classes de modelo de dados.

# Controlador GraphQL de Produtos

## Introdução

O `ProdutoGraphQLController` é um controlador Spring responsável por expor operações GraphQL relacionadas a produtos e categorias em um sistema de gerenciamento. Ele utiliza as anotações do Spring GraphQL para mapear consultas (`QueryMapping`) e mutações (`MutationMapping`) para métodos específicos no controlador.

```java
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
```

## Métodos Principais

### 1. **`listarProdutos`**

- **Descrição:** Lista todos os produtos cadastrados no sistema.
- **Anotação:** `@QueryMapping`.

### 2. **`listarProdutoPorId`**

- **Descrição:** Busca um produto pelo seu identificador único.
- **Parâmetro:**
  - `id` (String): Identificador único do produto.
- **Anotação:** `@QueryMapping` e `@Argument`.

### 3. **`listarCategorias`**

- **Descrição:** Lista todas as categorias cadastradas no sistema.
- **Anotação:** `@QueryMapping`.

### 4. **`listarCategoriaPorId`**

- **Descrição:** Busca uma categoria pelo seu identificador único.
- **Parâmetro:**
  - `id` (Long): Identificador único da categoria.
- **Anotação:** `@QueryMapping` e `@Argument`.

### 5. **`criarProduto`**

- **Descrição:** Cria um novo produto com base nos dados fornecidos.
- **Parâmetro:**
  - `produtoRequest` (ProdutoRequest): DTO contendo os dados do novo produto.
- **Anotação:** `@MutationMapping` e `@Argument`.

### 6. **`atualizarProduto`**

- **Descrição:** Atualiza os dados de um produto existente com base no identificador.
- **Parâmetros:**
  - `id` (String): Identificador único do produto a ser atualizado.
  - `produtoRequest` (ProdutoRequest): DTO contendo os novos dados do produto.
- **Anotação:** `@MutationMapping` e `@Argument`.

### 7. **`deletarProduto`**

- **Descrição:** Deleta um produto com base no identificador.
- **Parâmetro:**
  - `id` (String): Identificador único do produto a ser deletado.
- **Anotação:** `@MutationMapping` e `@Argument`.

## Dependências

- **`ProdutoRepository`:** Interface que estende `JpaRepository` e fornece métodos para acessar dados relacionados a produtos.
  
- **`CategoriaRepository`:** Interface que estende `JpaRepository` e fornece métodos para acessar dados relacionados a categorias.

## Uso Típico

O `ProdutoGraphQLController` fornece uma interface GraphQL para consultar, criar, atualizar e deletar produtos e categorias em um sistema de gerenciamento. Essas operações podem ser acessadas por meio de consultas e mutações GraphQL, permitindo a interação eficiente com o backend do aplicativo.


### Habilitando a Interface GraphiQL no Spring GraphQL

A propriedade `spring.graphql.graphiql.enabled=true` é uma configuração no ambiente Spring que habilita a interface de usuário interativa chamada GraphiQL para explorar e testar consultas GraphQL. Essa interface é uma ferramenta valiosa durante o desenvolvimento, pois permite a execução de consultas GraphQL diretamente no navegador.

#### Configuração

Para habilitar o GraphiQL em um aplicativo Spring GraphQL, é necessário adicionar a seguinte propriedade no arquivo `application.properties`:

```properties
spring.graphql.graphiql.enabled=true
```

Esta configuração informa ao Spring para ativar o suporte ao GraphiQL.

#### Acesso ao GraphiQL

Após habilitar o GraphiQL, a interface estará disponível em uma URL específica. A URL padrão geralmente segue o padrão:

```plaintext
http://localhost:8080/graphiql
```

No entanto, a porta pode variar dependendo da configuração do seu aplicativo. Vale ressaltar de substituir `8080` pela porta real em que o aplicativo está sendo executado.

**Acesse a URL do GraphiQL no navegador:**

Supondo que seu aplicativo esteja sendo executado em `http://localhost:8080`, logo poderá acessar o GraphiQL em:

   ```plaintext
   http://localhost:8080/graphiql
   ```

Isso abrirá a interface GraphiQL no navegador, permitindo que interaja com seu servidor GraphQL.

Durante o desenvolvimento, o GraphiQL oferece uma maneira fácil de explorar o esquema GraphQL, testar consultas e visualizar a documentação. Ele fornece sugestões de consulta, realces de sintaxe e ajuda contextual para facilitar o desenvolvimento e a depuração.

Ao utilizar essa configuração, o desenvolvedor pode aprimorar a eficiência ao testar consultas GraphQL sem a necessidade de ferramentas externas.

### Listar Todos os produtos

```graphql
query{
  listarProdutos{
    id
    nome
  }
}
```

```graphql
query {
  listarProdutos {
    id
    nome
    preco
    quantidade
    categoria {
      id
      nome
    }
  }
}
```

### Listar Categorias

```graphql
query{
  listarCategoria{
    id,
    nome
  }
}
```

### Listar produto por id

```graphql
query{
  listarProdutoPorId(id : "f82fc3b5-f69a-44ae-96a2-9e7bfa7eb8ea"){
    nome
    preco
    quantidade
    categoria{
      id
      nome
    }
  }
}
```

```graphql
query($id:String){
  listarProdutoPorId(id:$id){
    nome, 
    categoria{
      nome,
      produtos{
        nome
      }
    }
  }
}

Variables:
{
  "id": "560054e8-76f8-48a4-89b6-0b320ca37ec9"
}
```

### Listar categoria por id

```graphql
query{
  listarCategoriaPorId(id: 1){
    nome,
    produtos{
      nome
    }
  }
}
```

### Criar um novo produto

```graphql
mutation {
  criarProduto(produtoRequest: {
    nome: "Novo Produto"
    preco: 99.99
    quantidade: 10
    categoriaId: 1
  }) {
    id
    nome
    preco
    quantidade
    categoria {
      id
      nome
    }
  }
}
```

```graphql
mutation($n:String, $p:Float, $q:Int, $catId:Float){
  criarProduto(produtoRequest:{
    nome:$n,
    preco:$p,
    quantidade:$q,
    categoriaId:$catId
  }){
    nome,
    preco, 
    quantidade, 
    categoria{
      nome
    }
  }
}

Variables:
{
  "n": "P21",
  "p": 3400,
  "q": 3,
  "catId": 1
}
```

### Deletar produto

```graphql
mutation{
  deletarProduto(id: "341860ca-06d0-4945-a256-d322ae2aa8f4")
}
```

Este controlador fornece uma maneira eficiente e flexível de interagir com os dados do sistema por meio de consultas e mutações GraphQL.

# Autor
## Feito por: `Daniel Penelva de Andrade`