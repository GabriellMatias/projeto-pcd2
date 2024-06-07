package org.projetoPCD;

import org.projetoPCD.subClasses.ClienteSocketBiblioteca;
import org.projetoPCD.subClasses.ServidorSocketBiblioteca;

public class Main {

    public static final int DEFAULT_PORT = 12345;

    public static void iniciarServidor(int port) {
        Thread servidorThread = new Thread(() -> {
            ServidorSocketBiblioteca servidor = new ServidorSocketBiblioteca();
            servidor.start(port);
        });
        servidorThread.start();

        // Esperar o servidor iniciar
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    public static void iniciarCliente() {
        ClienteSocketBiblioteca cliente = new ClienteSocketBiblioteca();
        cliente.executarCliente();
    }

    public static void main(String[] args) {
        iniciarServidor(DEFAULT_PORT); // Inicia o servidor na porta padrão
        Thread servidorThread = Thread.currentThread();
        if (servidorThread.isAlive()) {
            iniciarCliente();
        }
    }


    public static void iniciarServidorParaTestes() {
        int port = findAvailablePort();
        iniciarServidor(port);
    }

    private static int findAvailablePort() {
        return 54321;
    }
}
