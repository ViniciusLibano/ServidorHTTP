package com.example.httpserver.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpVersionTest {
    @Test
    void getBestHttpVersionMatch() {
        HttpVersion version = null;
        try {
            version = HttpVersion.getCompatibleVersion("HTTP/1.1");
        } catch (BadHttpVersionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertNotNull(version);
        assertEquals(version, HttpVersion.HTTP_1_1);
    }

    @Test
    void getBestHttpVersionBadFormat() {
        HttpVersion version = null;
        try {
            version = HttpVersion.getCompatibleVersion("http/1.1");
        } catch (BadHttpVersionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void getBestHttpVersionHigherVersion() {
        HttpVersion version = null;
        try {
            version = HttpVersion.getCompatibleVersion("HTTP/1.2");
        } catch (BadHttpVersionException e) {
            // TODO Auto-generated catch block
            fail();
        }
    }
}
