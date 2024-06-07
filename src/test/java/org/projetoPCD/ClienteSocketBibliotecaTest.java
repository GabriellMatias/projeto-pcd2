package org.projetoPCD;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import junit.framework.TestCase;
import org.projetoPCD.subClasses.Livro;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClienteSocketBibliotecaTest extends TestCase {

    public void testAdicionarLivro() {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("CADASTRAR");
            out.println("{\"titulo\":\"Livro de Teste\",\"autor\":\"Autor de Teste\",\"genero\":\"Ficção\",\"numero_exemplares\":1}");

            String response = in.readLine();
            assertEquals("Livro cadastrado com sucesso.", response);

            // Agora vamos verificar se o livro foi realmente adicionado
            out.println("LISTAR");
            String listaLivros = in.readLine();
            List<Livro> livros = new Gson().fromJson(listaLivros, new TypeToken<List<Livro>>() {}.getType());

            boolean livroEncontrado = false;
            for (Livro livro : livros) {
                if (livro.getTitulo().equalsIgnoreCase("Livro de Teste")) {
                    livroEncontrado = true;
                    break;
                }
            }

            assertTrue("Livro não encontrado na lista.", livroEncontrado);

            // Fechar os recursos
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exceção lançada: " + e.getMessage());
        }
    }
}
