package Modelo;

import Enums.CodigosHTTP;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RespostaHTTP {
    private final String br = "\n\r";
    private HeaderHTTP header;
    private String file = "";
    
    public RespostaHTTP(HeaderHTTP header) {
        this.header = header;
    }
    
    private void readFile()
        throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("src/www" + header.getPath()));
            
            while (reader.ready()) {
                this.file += reader.readLine();
            }
    }
    
    public byte[] Reposta() {
        String resposta = "";
        
        try { 
            this.readFile();
        
            resposta = header.getHttpVersion() + " " + CodigosHTTP.OK + this.br + "Content-Length: " + this.file.getBytes().length + this.br + this.br + this.file + this.br + this.br;

            
        } catch (IOException e) {
            String erro = "<html><head><title>Erro 404</title></head><body><h1>Erro 404</h1><p>" + e.getMessage() +"</p></body></html>";
            resposta = header.getHttpVersion() + " " + CodigosHTTP.NOT_FOUND + this.br + "Content-Length " + erro.getBytes().length + this.br + this.br + erro + this.br + this.br;
        }
        
        return resposta.getBytes();
    }
}
