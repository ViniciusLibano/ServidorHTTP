package Modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final Integer PORT = 8082;
    private ServerSocket servidor;
    
    public void iniciar() {
        try {
            servidor = new ServerSocket(PORT);
            System.out.println("Escutando na porta: " + PORT);
            
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getLocalAddress().getHostAddress()+":"+cliente.getLocalPort());
                
                Thread t = new Thread(new HTTPHandler(cliente));
                t.start();
            }
            
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private class HTTPHandler implements Runnable {
        private final Socket socket;
        private InputStream in;
        private OutputStream out;
        
        protected HTTPHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
                
                HeaderParser parser = new HeaderParser(new BufferedReader(new InputStreamReader(in)).readLine());
                parser.LogReguest();
                
                String html = 
                        "<html>"+
                        "<head><link rel=\"stylesheet\" href=\"styles.css\"><title>Teste</title></head>"+
                        "<body><h1>Teste</h1></body>"+
                        "</html>";
                
                final String quebraLinha = "\n\r";
                
                String resposta = 
                        "HTTP/1.1 200 OK"+quebraLinha+ //status [http_version codigo mensagem]
                        "Content-Lenght: " + html.getBytes().length + quebraLinha+//header
                        quebraLinha+
                        html+
                        quebraLinha+quebraLinha;
                
                out.write(resposta.getBytes());
                
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Erro: "+e.getMessage());
            }
        }
    }
}
