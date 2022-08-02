package webserver.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class KeyValue {
    private static final String INVALID_PARAMETERS = "잘못된 파라미터";
    private static final String REGEX = "=";
    private static final int PARAM_SIZE = 2;
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private final String key;
    private final String value;

    private KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static KeyValue of(String params) {
        if (params == null || params.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PARAMETERS);
        }
        String[] tokens = params.split(REGEX);
        if (tokens.length != PARAM_SIZE) {
            return null;
        }
        return new KeyValue(tokens[KEY_INDEX], decode(tokens[VALUE_INDEX]));
    }

    private static String decode(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
