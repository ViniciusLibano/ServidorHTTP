package com.example.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket servidor;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.servidor = new ServerSocket(this.port);
    }

    @Override
    public void run(){
        try {
            while (servidor.isBound() && !servidor.isClosed()) {

                Socket cliente = servidor.accept();

                LOGGER.info("Cliente conectado: " + cliente.getInetAddress());

                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(cliente);
                workerThread.start();
            }

        } catch (IOException e) {
            LOGGER.info("Erro ao iniciar socket", e);
        } finally {
            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
