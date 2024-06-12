package com.example.httpserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP = 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A; 

    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParserException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request = new HttpRequest();

        try {
            parseRequestLine(reader, request);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        parseHeaders(reader, request);
        parseBody(reader, request);

        return request;
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParserException {
        StringBuilder processDataBuffer = new StringBuilder();
        
        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        int _byte;
        while((_byte = reader.read()) >= 0) {
            if (_byte == CR) {
                _byte = reader.read();

                if (_byte == LF) {
                    
                    LOGGER.debug("Request Line to Process : {}", processDataBuffer.toString());

                    if (!methodParsed || !requestTargetParsed) {
                        throw new HttpParserException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }

                    return;
                } else {
                    throw new HttpParserException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
            }

            if (_byte == SP) {
                if (!methodParsed) {
                    LOGGER.debug("Request Line METHOD to Process : {}", processDataBuffer.toString());
                    request.setMethod(processDataBuffer.toString());
                    methodParsed = true;
                } else if (!requestTargetParsed){
                    LOGGER.debug("Request Line REQUEST TARGET to Process : {}", processDataBuffer.toString());
                    requestTargetParsed = true;
                } else {
                    throw new HttpParserException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
                processDataBuffer.delete(0, processDataBuffer.length());

            } else {

                processDataBuffer.append((char)_byte);

                if (!methodParsed) {

                    if (processDataBuffer.length() > HttpMethod.MAX_LENGTH) {

                        throw new HttpParserException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);

                    }

                }

            }
            
        }
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) {

    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {

    }
}
