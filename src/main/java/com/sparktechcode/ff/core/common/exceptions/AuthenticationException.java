package com.sparktechcode.ff.core.common.exceptions;

import com.sparktechcode.springjpasearch.exceptions.BaseSparkException;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends BaseSparkException {

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.UNAUTHORIZED;

    public AuthenticationException() {
        super(DEFAULT_STATUS, FFError.WRONG_CREDENTIALS, "Authorization failed.");
    }

    public AuthenticationException(FFError error, String message) {
        super(DEFAULT_STATUS, error, message);
    }

    public AuthenticationException(FFError error, String message, Object data) {
        super(DEFAULT_STATUS, error, message);
        setData(data);
    }
}
