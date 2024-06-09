package Modelo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    private final Integer PORT = 8082;
    private ServerSocket servidor;
    
    public void iniciar() {
        try {
            servidor = new ServerSocket(PORT);
            System.out.println("Escutando na porta: " + PORT);
            
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getLocalAddress().getHostAddress()+":"+cliente.getLocalPort());
                
                Thread t = new Thread(new HandlerHTTP(cliente));
                t.start();
            }
            
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private class HandlerHTTP implements Runnable {
        private final Socket socket;
        private final Integer limiteRequest = 800;
        private InputStream in;
        private OutputStream out;
        
        protected HandlerHTTP(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                in = socket.getInputStream(); out = socket.getOutputStream();
                
                String headers = "";
                for (int i = 0; i < this.limiteRequest; i++) {
                    headers+=(char)(in.read());
                }
                
                RequestHTTP request = new RequestHTTP(headers);
                request.logHeaders();
                RespostaHTTP resposta = new RespostaHTTP(request);
                out.write(resposta.Reposta());
                
                in.close(); out.close(); socket.close();
            } catch (IOException e) {
                System.out.println("Erro: "+e.getMessage());
            } catch (Exception ex) {
                System.out.println("Erro: "+ex.getMessage());
            }
        }
    }
}
