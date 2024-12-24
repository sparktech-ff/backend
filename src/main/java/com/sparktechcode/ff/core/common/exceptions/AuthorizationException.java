package com.sparktechcode.ff.core.common.exceptions;

import com.sparktechcode.springjpasearch.exceptions.BaseSparkException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends BaseSparkException {

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.FORBIDDEN;

    public AuthorizationException() {
        super(DEFAULT_STATUS, FFError.PERMISSIONS_ERROR, "Forbidden resource.");
    }

    public AuthorizationException(FFError error, String message) {
        super(DEFAULT_STATUS, error, message);
    }

    public AuthorizationException(FFError error, String message, Object data) {
        super(DEFAULT_STATUS, error, message);
        setData(data);
    }
}

