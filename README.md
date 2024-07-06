# Biblioteca API - Desafio Design Patterns dio

Esta é uma API de Biblioteca desenvolvida em Spring Boot que permite a gestão de empréstimos de livros. A API suporta operações para listar livros disponíveis, emprestar livros, listar clientes, buscar empréstimos por cliente e devolver livros. 
Foi elaborada visando praticar os padrões Singleton, Strategy e Facade abordados no desafio do bootcamp dio.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.1
- H2 Database
- Maven

## Configuração do Ambiente de Desenvolvimento

1. **Clone o Repositório**
   ```bash
   git clone https://github.com/seu-usuario/biblioteca-api.git
   cd biblioteca-api
   ```

2. **Compile e Execute a Aplicação**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   
## Endpoints da API

### Listar Livros Disponíveis

**GET /**
```bash
curl -X GET "http://localhost:8080/" -H "accept: application/json"
```

### Emprestar Livro

**POST /emprestar**
```bash
curl -X POST "http://localhost:8080/emprestar?idCliente=1&idLivro=1" -H "accept: application/json"
```

### Listar Clientes

**GET /clientes**
```bash
curl -X GET "http://localhost:8080/clientes" -H "accept: application/json"
```

### Buscar Empréstimo por Cliente

**GET /clientes/{id}**
```bash
curl -X GET "http://localhost:8080/clientes/{id}" -H "accept: application/json"
```

### Listar Empréstimos

**GET /emprestimos**
```bash
curl -X GET "http://localhost:8080/emprestimos" -H "accept: application/json"
```

### Prorrogar Empréstimo

**POST /emprestimos/prorrogar/{id}**
```bash
curl -X POST "http://localhost:8080/emprestimos/prorrogar/{id}" -H "accept: application/json"
```

### Devolver Livro

**POST /devolver/{id}**
```bash
curl -X POST "http://localhost:8080/devolver/{id}" -H "accept: application/json"
```

## Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── dio/
│   │       └── example/
│   │           └── biblioteca/
│   │               ├── controller/
│   │               │   └── BibliotecaController.java
│   │               ├── model/
│   │               │   ├── Cliente.java
│   │               │   ├── Emprestimo.java
│   │               │   └── Livro.java
│   │               ├── repository/
│   │               │   ├── ClienteRepository.java
│   │               │   ├── EmprestimoRepository.java
│   │               │   └── LivroRepository.java
│   │               ├── service/
│   │               │   ├── impl/
│   │               │   │  ├── BibliotecaServiceImpl.java
│   │               │   └── BibliotecaService.java
│   │               ├── DataInitializer.java
│   │               └── BibliotecaApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── dio/
            └── example/
                └── biblioteca/
                    └── BibliotecaApiApplicationTests.java
```


## 

<p align="center">Feito com ❤️ por <a href="https://www.linkedin.com/in/bianca-sanchesdev/)">Bianca</a> </p>
