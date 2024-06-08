package http;

import Modelo.TCPServer;

public class HTTP {

    public static void main(String[] args) {
        TCPServer srv = new TCPServer();
        srv.iniciar();
    }
    
    
}
