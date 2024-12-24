package com.sparktechcode.ff.core.common.exceptions;

import com.sparktechcode.springjpasearch.exceptions.BaseSparkException;
import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseSparkException {

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerException() {
        super(DEFAULT_STATUS, FFError.UNKNOWN_ERROR, "Something went wrong");
    }

    public InternalServerException(FFError error, String message) {
        super(DEFAULT_STATUS, error, message);
    }

    public InternalServerException(FFError error, String message, Object data) {
        super(DEFAULT_STATUS, error, message);
        setData(data);
    }
}

