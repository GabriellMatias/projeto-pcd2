package org.projetoPCD;

import org.projetoPCD.subClasses.ClienteSocketBiblioteca;
import org.projetoPCD.subClasses.ServidorSocketBiblioteca;

public class Main {

    public static void iniciarServidor() {
        Thread servidorThread = new Thread(() -> {
            ServidorSocketBiblioteca servidor = new ServidorSocketBiblioteca();
            servidor.start();
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
        iniciarServidor();

        // Verificar se o servidor está ativo antes de iniciar o cliente
        Thread servidorThread = Thread.currentThread();
        if (servidorThread.isAlive()) {
            iniciarCliente();
        }
    }
}

