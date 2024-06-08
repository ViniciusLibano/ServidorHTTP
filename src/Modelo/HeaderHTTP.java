package Modelo;

public class HeaderHTTP {
    private String method;
    private String path;
    private String httpVersion;
    
    public HeaderHTTP(String strRequest) {
        String[] arrayRequest = strRequest.split(" ");
        this.method = arrayRequest[0];
        this.path = arrayRequest[1]; if(this.path.equals("/")){this.path = "/index.html";}
        this.httpVersion = arrayRequest[2];
    }
    
    public String getPath() {
        return this.path;
    }

    public String getMethod() {
        return method;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
    
    public void LogReguest() {
        System.out.println("--- HTTP request ----------");
        System.out.println("Metodo: "+this.method);
        System.out.println("Path..: "+this.path);
        System.out.println("Versao: "+this.httpVersion);
        System.out.println("---------------------------");
    }
}
