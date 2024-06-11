package com.example.httpserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.httpserver.config.Configuracao;
import com.example.httpserver.config.GerenciadorConfig;
import com.example.httpserver.core.ServerListenerThread;

public class HttpServer {

    private final static Logger LOGGER =  LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        LOGGER.info("Iniciando servidor");

        ServerListenerThread servidor;
        GerenciadorConfig.Instancia().carregarArquivoConfig("C:\\dev\\ServidorHTTP\\httpserver\\src\\main\\resources\\http.json");
        Configuracao conf = GerenciadorConfig.Instancia().configuracaoAtual();
        
        LOGGER.info("Porta: " + conf.getPort());
        LOGGER.info("WebRoot: " + conf.getWebroot());

        try {
            servidor = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            servidor.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
