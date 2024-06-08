package Modelo;

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
            
            System.out.println(this.file);
    }
    
    public byte[] Reposta() throws IOException {
        this.readFile();
        
        String resposta = header.getHttpVersion() + " " + "200" + this.br + "Content-Length: " + this.file.getBytes().length + this.br + this.br + this.file + this.br + this.br;

        return resposta.getBytes();
    }
}
