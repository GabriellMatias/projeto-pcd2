# Projeto: Biblioteca com Sockets em Java 📚🖥️
Bem-vindo ao projeto de Biblioteca com Sockets em Java! Este projeto implementa um servidor que gerencia o cadastro de livros em uma biblioteca, permitindo listar, alugar, devolver e cadastrar livros através de um cliente socket. Vamos explorar cada parte deste projeto passo a passo.

# Introdução
Este projeto foi desenvolvido em Java 17 e utiliza sockets para a comunicação entre cliente e servidor. A biblioteca gerencia livros armazenados em um arquivo JSON, onde cada livro possui os seguintes atributos:

- Autor 📖
- Nome 📚
- Gênero 📂
- Número de Exemplares 🔢

#### As alterações feitas pelo usuário, como o cadastro de novos livros e o aluguel/devolução, são refletidas diretamente no arquivo JSON.

# Estrutura do Projeto
A estrutura do projeto está organizada da seguinte forma:
```
biblioteca-socket-java
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── cliente
│   │   │   │   └── Cliente.java
│   │   │   ├── servidor
│   │   │   │   ├── Biblioteca.java
│   │   │   │   ├── Livro.java
│   │   │   │   ├── Servidor.java
│   │   │   │   └── UtilidadesJSON.java
│   │   └── resources
│   │       └── livros.json
│   └── test
│       └── java
│           └── TesteBiblioteca.java
├── README.md
└── pom.xml

```

# Funcionalidades
## Listagem de Livros
O servidor permite que o cliente solicite a listagem de todos os livros disponíveis na biblioteca. A resposta inclui o nome, autor, gênero e o número de exemplares disponíveis de cada livro.

## Aluguel de Livros
Os clientes podem alugar livros. O servidor verifica se há exemplares disponíveis e, se positivo, decrementar o número de exemplares disponíveis e retorna uma confirmação.

## Devolução de Livros
Os clientes podem devolver livros alugados. O servidor incrementa o número de exemplares disponíveis e retorna uma confirmação.

## Cadastro de Livros
Os clientes podem cadastrar novos livros na biblioteca. O servidor adiciona o novo livro ao arquivo JSON e confirma a operação.




# Instruções para Execução
### Clonar o Repositório:
```
git clone https://github.com/seu-usuario/biblioteca-socket-java.git
cd biblioteca-socket-java
 ```

### Compilar o Projeto:

```
mvn clean install
```

### Executar o cliente:

```
Execute a Main
```

# Estrutura do JSON
O arquivo livros.json contém uma lista de objetos livro. Cada objeto possui a seguinte estrutura:

```
[
  {
    "autor": "Autor Exemplo",
    "nome": "Nome do Livro",
    "genero": "Gênero do Livro",
    "numeroExemplares": 5
  },
  {
    "autor": "Outro Autor",
    "nome": "Outro Livro",
    "genero": "Outro Gênero",
    "numeroExemplares": 3
  }
]
```















