package com.example.httpserver.config;

class HttpConfigurationException extends RuntimeException {

    public HttpConfigurationException() {
    }
    
    public HttpConfigurationException(String message, Throwable cause) {
        super(message);
    }
    
    public HttpConfigurationException(String message) {
        super(message);
    }
    
    public HttpConfigurationException(Throwable cause){
        super(cause);
    }
}
