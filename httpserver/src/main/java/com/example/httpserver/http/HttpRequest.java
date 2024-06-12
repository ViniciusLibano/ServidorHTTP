package com.example.httpserver.http;

public class HttpRequest extends HttpMessage{
    private HttpMethod method;
    private String requestTarget;
    private String originalHttpVersion;
    private HttpVersion bestCompatibleHttpVersion;

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

    public String getRequestTarget() {
        return requestTarget;
    }

    void setRequestTarget(String requestTarget) throws HttpParserException {
        if (requestTarget == null || requestTarget.length() == 0) {
            throw new HttpParserException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }

        this.requestTarget = requestTarget;
    }

    public String getOriginalHttpVersion() {
        return originalHttpVersion;
    }

    public void setOriginalHttpVersion(String originalHttpVersion) throws BadHttpVersionException, HttpParserException {
        this.originalHttpVersion = originalHttpVersion;
        this.bestCompatibleHttpVersion = HttpVersion.getCompatibleVersion(originalHttpVersion);

        if (this.bestCompatibleHttpVersion == null) {
            throw new HttpParserException(HttpStatusCode.SERVER_ERROR_505_VERSION_NOT_SUPPORTED);
        }
    }

    
}
