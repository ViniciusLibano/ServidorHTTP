package com.example.httpserver.http;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1", 1,1);

    private final String LITERAL;
    private final int MAJOR;
    private final int MINOR;

    HttpVersion(String LITERAL, int MAJOR, int MINOR) {
        this.LITERAL = LITERAL;
        this.MAJOR = MAJOR;
        this.MINOR = MINOR;
    }

    private static final Pattern httVersionRegexPattern = Pattern.compile("^HTTP/(?<major>\\d+).(?<minor>\\d+)");

    public static HttpVersion getCompatibleVersion(String literalVersion) throws BadHttpVersionException {
        Matcher matcher = httVersionRegexPattern.matcher(literalVersion);

        if (!matcher.find() || matcher.groupCount() != 2) {
            throw new BadHttpVersionException();
        }

        int major = Integer.parseInt(matcher.group("major"));
        int minor = Integer.parseInt(matcher.group("minor"));

        HttpVersion temp = null;
        for (HttpVersion version : HttpVersion.values()) {
            if (version.LITERAL.equals(literalVersion)) {
                return version;
            } else {
                if (version.MAJOR == major) {
                    if (version.MINOR < minor) {
                        temp = version;
                    }
                }
            }
        }

        return temp;
    }
}
