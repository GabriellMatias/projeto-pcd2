package org.projetoPCD;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testIniciarServidor() {
        // Verificar se o servidor é iniciado corretamente na porta padrão
        Main.iniciarServidor(Main.DEFAULT_PORT);
    }

    @Test
    public void testIniciarCliente() {
        // Verificar se o cliente é iniciado apenas quando o servidor está ativo
        Main.iniciarServidorParaTestes(); // Iniciar o servidor em uma porta diferente
        // Esperar um pouco para garantir que o servidor tenha tempo para iniciar completamente
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.iniciarCliente(); // Tentar iniciar o cliente após o servidor estar ativo
    }
}
