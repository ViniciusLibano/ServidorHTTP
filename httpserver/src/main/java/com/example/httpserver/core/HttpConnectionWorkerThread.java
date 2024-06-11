package com.example.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    private Socket cliente;

    public HttpConnectionWorkerThread(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outpuStream = null;
        try {
            inputStream = this.cliente.getInputStream();
            outpuStream = this.cliente.getOutputStream();

            String html = "<html><head><title>Teste http</title></head><body><h1>Hello World!</h1></body></html>";
            final String CRLF = "\n\r";
            String resposta = 
                "HTTP/1.1 200 OK" + CRLF + //status : versao http + cod response + mensagem
                "Content-Length: " + html.getBytes().length + CRLF + //header
                CRLF +
                html +
                CRLF + CRLF;

            outpuStream.write(resposta.getBytes());

            
            LOGGER.info("Fim do processamento da conexão.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null ) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outpuStream != null ) {
                try {
                    outpuStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (this.cliente != null ) {
                try {
                    this.cliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
