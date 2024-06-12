package com.example.httpserver.http;

import com.example.httpserver.http.HttpParserException;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        httpParser = new HttpParser();
    }

    @Test
    void testParseHttpRequest() {
        HttpRequest request = null;
        
        try {
            request = httpParser.parseHttpRequest(gerarRequest());    
        } catch (HttpParserException e) {
            fail(e);
        }
        
        
        assertEquals(request.getMethod(), HttpMethod.GET);
    }

    @Test
    void testParseHttpRequestBadMethod1() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(gerarBadRequest());
            fail();
        } catch (HttpParserException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
        
    }

    @Test
    void testParseHttpRequestBadMethod2() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(gerarBadRequestMethodNameLength());
            fail();
        } catch (HttpParserException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
        
    }

    @Test
    void testParseHttpRequestInvalidItems1() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(gerarBadRequestInvalidItems());
            fail();
        } catch (HttpParserException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
        
    }

    @Test
    void testParseHttpRequestEmptyRequestLine1() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(gerarBadRequestEmptyRequestLine());
            fail();
        } catch (HttpParserException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
        
    }

    @Test
    void testParseHttpRequestNoLF() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(gerarBadRequestEmptyRequestLine());
            fail();
        } catch (HttpParserException e) {
            assertEquals(e.getErrorCode(), HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
        }
        
    }

    private InputStream gerarRequest() {
        String valorTeste = 
            "GET /hello.html HTTP/1.1\r\n"+
            "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"+
            "Host: www.tutorialspoint.com\r\n"+
            "Accept-Language: en-us\r\n"+
            "Accept-Encoding: gzip, deflate\r\n"+
            "Connection: Keep-Alive\r\n"+
            "\r\n";

        InputStream inputStream = new ByteArrayInputStream(valorTeste.getBytes(StandardCharsets.US_ASCII));

        

        return inputStream;
    }

    private InputStream gerarBadRequest() {
        String valorTeste = 
            "GeT /hello.html HTTP/1.1\r\n"+
            "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"+
            "Host: www.tutorialspoint.com\r\n"+
            "Accept-Language: en-us\r\n"+
            "Accept-Encoding: gzip, deflate\r\n"+
            "Connection: Keep-Alive\r\n"+
            "\r\n";

        InputStream inputStream = new ByteArrayInputStream(valorTeste.getBytes(StandardCharsets.US_ASCII));

        

        return inputStream;
    }

    private InputStream gerarBadRequestMethodNameLength() {
        String valorTeste = 
            "GeTaaaaaaaaaa /hello.html HTTP/1.1\r\n"+
            "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"+
            "Host: www.tutorialspoint.com\r\n"+
            "Accept-Language: en-us\r\n"+
            "Accept-Encoding: gzip, deflate\r\n"+
            "Connection: Keep-Alive\r\n"+
            "\r\n";

        InputStream inputStream = new ByteArrayInputStream(valorTeste.getBytes(StandardCharsets.US_ASCII));

        

        return inputStream;
    }

    private InputStream gerarBadRequestInvalidItems() {
        String valorTeste = 
            "GET / aaaaaaa HTTP/1.1\r\n"+
            "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"+
            "Host: www.tutorialspoint.com\r\n"+
            "Accept-Language: en-us\r\n"+
            "Accept-Encoding: gzip, deflate\r\n"+
            "Connection: Keep-Alive\r\n"+
            "\r\n";

        InputStream inputStream = new ByteArrayInputStream(valorTeste.getBytes(StandardCharsets.US_ASCII));

        

        return inputStream;
    }

    private InputStream gerarBadRequestEmptyRequestLine() {
        String valorTeste = 
            "\r\n"+
            "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"+
            "Host: www.tutorialspoint.com\r\n"+
            "Accept-Language: en-us\r\n"+
            "Accept-Encoding: gzip, deflate\r\n"+
            "Connection: Keep-Alive\r\n"+
            "\r\n";

        InputStream inputStream = new ByteArrayInputStream(valorTeste.getBytes(StandardCharsets.US_ASCII));

        

        return inputStream;
    }

    private InputStream gerarRequestNoLF() {
        String valorTeste = 
            "GET /hello.html HTTP/1.1\r"+
            "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n"+
            "Host: www.tutorialspoint.com\r\n"+
            "Accept-Language: en-us\r\n"+
            "Accept-Encoding: gzip, deflate\r\n"+
            "Connection: Keep-Alive\r\n"+
            "\r\n";

        InputStream inputStream = new ByteArrayInputStream(valorTeste.getBytes(StandardCharsets.US_ASCII));

        

        return inputStream;
    }
}
