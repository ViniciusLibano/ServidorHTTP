package com.example.httpserver.http;

import java.io.InputStream;
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
        httpParser.parseHttpRequest(gerarValor());
    }

    private InputStream gerarValor() {
        String valorTeste = 
            "GET /hello.htm HTTP/1.1\r\n"+
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
