package com.example.httpserver.http;

public class HttpRequest extends HttpMessage{
    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest() {
    }

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String method) throws HttpParserException {
        for (HttpMethod httpMethod: HttpMethod.values()) {
            if (method.equals(httpMethod.name())) {
                this.method = httpMethod;
                return;
            }
        }
        throw new HttpParserException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
    }

    
}
