package com.example.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.example.httpserver.config.Configuracao;
import com.example.httpserver.config.GerenciadorConfig;

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        GerenciadorConfig.Instancia().carregarArquivoConfig("httpserver\\src\\main\\resources\\http.json");
        Configuracao conf = GerenciadorConfig.Instancia().configuracaoAtual();

        try {
            ServerSocket serverSocket =  new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outpuStream = socket.getOutputStream();

            String html = "<html><head><title>Teste http</title></head><body><h1>Hello World!</h1></body></html>";
            final String CRLF = "\n\r";
            String resposta = 
                "HTTP/1.1 200 OK" + CRLF + //status : versao http + cod response + mensagem
                "Content-Length: " + html.getBytes().length + CRLF + //header
                CRLF +
                html +
                CRLF + CRLF;

            outpuStream.write(resposta.getBytes());

            inputStream.close();
            outpuStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
