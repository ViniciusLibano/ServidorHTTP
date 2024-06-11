package com.example.httpserver.http;

public class HttpParserException extends Exception {
    
    private final HttpStatusCode errorCode;

    public HttpParserException(HttpStatusCode errCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errCode;
    }

    public HttpStatusCode getErrorCode() {
        return this.errorCode;
    }
}
