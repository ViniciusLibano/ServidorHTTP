package Enums;

public enum CodigosHTTP {
    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404);
    
    public int errNum;
    
    CodigosHTTP(int erro) {
        errNum = erro;
    }
}
