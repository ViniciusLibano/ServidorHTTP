package Modelo;

public class RequestHTTP {
    
    private String[] headers;
    private String method;
    private String path;
    private String httpv;
    
    public RequestHTTP(String headers) throws Exception {
        this.headers = headers.split("\n");
        
        String[] primeiraLinha = this.headers[0].split(" ");
        this.method = primeiraLinha[0];
        this.httpv  = primeiraLinha[2];
        String indexCatch = primeiraLinha[1].split("\\?")[0];
        if(indexCatch.equals("/")){ this.path = "/index.html"; } else {this.path = indexCatch;}
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHttpv() {
        return httpv;
    }
    
    
    
    public void logHeaders() {
        for (int i = 0; i < this.headers.length; i++) {
            System.out.println(this.headers[i]);
        }
    }
}
