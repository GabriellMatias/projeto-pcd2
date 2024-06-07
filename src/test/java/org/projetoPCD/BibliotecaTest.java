package org.projetoPCD;

import junit.framework.TestCase;
import org.projetoPCD.subClasses.Biblioteca;
import org.projetoPCD.subClasses.Livro;

public class BibliotecaTest extends TestCase {

    public void testAlugarDevolverLivro() {
        Biblioteca biblioteca = new Biblioteca();

        // Adicionando um livro de teste à biblioteca
        Livro livroTeste = new Livro("Livro de Teste", "Autor de Teste", "Ficção", 1);
        biblioteca.adicionarLivro(livroTeste);

        // Teste de alugar um livro
        assertTrue(biblioteca.alugarLivroPorTitulo("Livro de Teste"));

        // Teste de tentar alugar o mesmo livro novamente
        assertFalse(biblioteca.alugarLivroPorTitulo("Livro de Teste"));

        // Teste de devolver um livro
        assertTrue(biblioteca.devolverLivroPorTitulo("Livro de Teste"));

        // Teste de tentar devolver um livro que não está alugado
        assertFalse(biblioteca.devolverLivroPorTitulo("Livro de Teste"));

    }
}
