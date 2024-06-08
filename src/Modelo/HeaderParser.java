package Modelo;

public class HeaderParser {
    private String quebraLinha = "\n\r";
    private String method;
    private String path;
    private String httpVersion;
    
    private enum CodigosHTTP {
        OK(200),
        BAD_REQUEST(400),
        NOT_FOUND(404),
        INTERNAL_ERROR(500);
        
        public int NumCod;
        
        CodigosHTTP(int NumCod) {
            this.NumCod=NumCod;
        }
    }
    
    public HeaderParser(String strRequest) {
        String[] arrayRequest = strRequest.split(" ");
        this.method = arrayRequest[0];
        this.path = arrayRequest[1];
        this.httpVersion = arrayRequest[2];
    }
    
    public void LogReguest() {
        System.out.println("--- HTTP request ----------");
        System.out.println("Metodo: "+this.method);
        System.out.println("Path..: "+this.path);
        System.out.println("Versao: "+this.httpVersion);
        System.out.println("---------------------------");
    }
}
