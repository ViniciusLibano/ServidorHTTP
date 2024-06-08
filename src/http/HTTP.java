package http;

import Modelo.ServerTCP;

public class HTTP {

    public static void main(String[] args) {
        ServerTCP srv = new ServerTCP();
        srv.iniciar();
    }
    
    
}
