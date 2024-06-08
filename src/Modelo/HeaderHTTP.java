package Modelo;

public class HeaderHTTP {
    private final String method;
    private final String httpVersion;
    private final String path;
    private final String[] param;
    
    public HeaderHTTP(String strRequest) throws Exception {
        String[] arrayRequest = strRequest.split(" ");
        String[] url = this.parseURL(arrayRequest[1]);
        
        if (url[0].equals("/"))
            {this.path = "/index.html";}
        else 
            {this.path = url[0];}
        
        this.method = arrayRequest[0];
        this.httpVersion = arrayRequest[2];
        this.param = url;
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

    public String[] getParam() {
        return param;
    }
    
    private String[] parseURL(String url) throws Exception {
        String[] param = url.split("\\?");
        
        return param;
    }
    
    public void LogReguest() {
        String allParam = "";
        
        for (var i = 1; i < this.param.length; i++) {
            allParam+=this.param[i]+" ";
        }
        
        System.out.println("--- HTTP request ----------");
        System.out.println("Metodo: "+this.method);
        System.out.println("Path..: "+this.path);
        System.out.println("Versao: "+this.httpVersion);
        System.out.println("Param.: "+allParam);
        System.out.println("---------------------------");
    }
}
