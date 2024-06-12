package com.example.httpserver.http;

public class HttpParserException extends Exception {
    
    private final HttpStatusCode errCode;

    public HttpParserException(HttpStatusCode errCode) {
        super(errCode.MESSAGE);
        this.errCode = errCode;
    }

    public HttpStatusCode getErrorCode() {
        return this.errCode;
    }
}
