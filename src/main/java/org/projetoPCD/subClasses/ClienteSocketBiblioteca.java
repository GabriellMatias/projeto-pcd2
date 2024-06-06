package org.projetoPCD.subClasses;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClienteSocketBiblioteca {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public void executarCliente() {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            String userInput;
            while (true) {
                exibirMenu();
                userInput = scanner.nextLine();

                if ("SAIR".equalsIgnoreCase(userInput)) {
                    break;
                }

                processarOpcaoUsuario(userInput, out, in, scanner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exibirMenu() {
        System.out.println("+----------------------------------------+");
        System.out.println("| Opção    | Descrição                    |");
        System.out.println("+----------------------------------------+");
        System.out.println("| LISTAR   | Listar todos os livros       |");
        System.out.println("| CADASTRAR| Cadastrar um novo livro      |");
        System.out.println("| ALUGAR   | Alugar um livro              |");
        System.out.println("| DEVOLVER | Devolver um livro alugado    |");
        System.out.println("| SAIR     | Encerrar o programa          |");
        System.out.println("+----------------------------------------+");
        System.out.println("Escolha uma opção:");
    }

    private void processarOpcaoUsuario(String opcao, PrintWriter out, BufferedReader in, Scanner scanner) throws IOException {
        switch (opcao.toUpperCase()) {
            case "LISTAR":
                listarLivros(out, in);
                break;

            case "CADASTRAR":
                cadastrarLivro(out, in, scanner);
                break;

            case "ALUGAR":
                alugarLivro(out, in, scanner);
                break;

            case "DEVOLVER":
                devolverLivro(out, in, scanner);
                break;

            default:
                System.out.println("Comando desconhecido.");
                break;
        }
    }

    private void listarLivros(PrintWriter out, BufferedReader in) throws IOException {
        out.println("LISTAR");
        String listaLivrosJson = in.readLine();
        Type listType = new TypeToken<List<Livro>>() {}.getType();
        List<Livro> livros = new Gson().fromJson(listaLivrosJson, listType);

        System.out.println("Livros disponíveis:");
        if (livros != null && !livros.isEmpty()) {
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.printf("%-25s | %-20s | %-20s | %s%n", "Título", "Autor", "Gênero", "Número de Exemplares");
            System.out.println("--------------------------------------------------------------------------------------------------");
            for (Livro livro : livros) {
                exibirLivroFormatado(livro);
            }
            System.out.println("--------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Nenhum livro disponível.");
        }
    }

    private void exibirLivroFormatado(Livro livro) {
        System.out.printf("%-25s | %-20s | %-20s | %d%n", livro.getTitulo(), livro.getAutor(), livro.getGenero(), livro.getNumeroExemplares());
    }

    private void cadastrarLivro(PrintWriter out, BufferedReader in, Scanner scanner) throws IOException {
        System.out.println("Para cadastrar um novo livro, por favor, forneça as seguintes informações:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("Número de exemplares: ");
        int numeroExemplares = Integer.parseInt(scanner.nextLine());

        Livro novoLivro = new Livro(titulo, autor, genero, numeroExemplares);
        out.println("CADASTRAR:" + new Gson().toJson(novoLivro));
        System.out.println(in.readLine());
    }

    private void alugarLivro(PrintWriter out, BufferedReader in, Scanner scanner) throws IOException {
        System.out.println("Digite o título do livro para alugar:");
        String tituloAlugar = scanner.nextLine();
        out.println("ALUGAR:" + tituloAlugar);
        String resposta = in.readLine();
        System.out.println(resposta);
    }

    private void devolverLivro(PrintWriter out, BufferedReader in, Scanner scanner) throws IOException {
        System.out.println("Digite o título do livro para devolver:");
        String tituloDevolver = scanner.nextLine();
        out.println("DEVOLVER:" + tituloDevolver);
        String resposta = in.readLine();
        System.out.println(resposta);
    }
}
