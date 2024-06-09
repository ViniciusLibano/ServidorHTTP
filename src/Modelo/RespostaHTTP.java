package Modelo;

import Enums.CodigosHTTP;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RespostaHTTP {
    private final String endLine = "\n\r";
    private RequestHTTP request;
    private String file = "";
    
    public RespostaHTTP(RequestHTTP header) {
        this.request = header;
    }
    
    private void readFile()
        throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("src/www" + request.getPath()));
            
            while (reader.ready()) {
                this.file += reader.readLine();
            }
    }
    
    public byte[] Reposta() {
        String resposta = "";
        
        try { 
            this.readFile();
        
            resposta = request.getHttpv()+ " " + CodigosHTTP.OK + this.endLine + "Content-Length: " + this.file.getBytes().length + this.endLine + this.endLine + this.file + this.endLine + this.endLine;

            
        } catch (IOException e) {
            String erro = "<html><head><title>Erro 404</title></head><body><h1>Erro 404</h1><p>" + e.getMessage() +"</p></body></html>";
            resposta = request.getHttpv()+ " " + CodigosHTTP.NOT_FOUND + this.endLine + "Content-Length " + erro.getBytes().length + this.endLine + this.endLine + erro + this.endLine + this.endLine;
        }
        
        return resposta.getBytes();
    }
}
