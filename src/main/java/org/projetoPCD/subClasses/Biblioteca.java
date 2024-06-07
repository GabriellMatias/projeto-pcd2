package org.projetoPCD.subClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private static final String CAMINHO_ARQUIVO = System.getProperty("user.dir") + "/src/main/java/org/projetoPCD/subClasses/files/biblioteca.json";
    private List<Livro> listaDeLivros;
    private final Gson conversorJson;

    public Biblioteca() {
        this.conversorJson = new GsonBuilder().setPrettyPrinting().create();
        this.listaDeLivros = new ArrayList<>();
        carregarListaDeLivros();
    }

    private void carregarListaDeLivros() {
        System.out.println("Diretório de execução: " + System.getProperty("user.dir"));
        System.out.println(CAMINHO_ARQUIVO);
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            try (Reader leitor = new FileReader(arquivo)) {
                Type tipoListaDeLivros = new TypeToken<ArrayList<Livro>>() {}.getType();
                this.listaDeLivros = conversorJson.fromJson(leitor, tipoListaDeLivros);
            } catch (IOException e) {
                e.fillInStackTrace();
            }
        } else {
            System.err.println("Arquivo " + CAMINHO_ARQUIVO + " não encontrado.");
        }
    }

    private void salvarListaDeLivros() {
        try (Writer escritor = new FileWriter(CAMINHO_ARQUIVO)) {
            conversorJson.toJson(listaDeLivros, escritor);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public List<Livro> listarTodosOsLivros() {
        return listaDeLivros;
    }

    public boolean adicionarLivro(Livro livro) {
        for (Livro livroExistente : listaDeLivros) {
            if (livroExistente.getTitulo().equalsIgnoreCase(livro.getTitulo())) {
                return false; // Livro já existe na lista
            }
        }
        listaDeLivros.add(livro);
        salvarListaDeLivros();
        return true;
    }

    public boolean alugarLivroPorTitulo(String titulo) {
        for (Livro livro : listaDeLivros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && livro.getNumeroExemplares() > 0) {
                livro.setNumeroExemplares(livro.getNumeroExemplares() - 1);
                livro.setAlugado(true); // Marcar o livro como alugado
                salvarListaDeLivros();
                return true;
            }
        }
        return false;
    }

    public boolean devolverLivroPorTitulo(String titulo) {
        for (Livro livro : listaDeLivros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                if (livro.isAlugado()) {
                    livro.setNumeroExemplares(livro.getNumeroExemplares() + 1);
                    livro.setAlugado(false); // Marcar o livro como não alugado
                    salvarListaDeLivros();
                    return true;
                } else {
                    return false; // Livro não estava alugado
                }
            }
        }
        return false; // Livro não encontrado
    }
}
