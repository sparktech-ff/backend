package com.sparktechcode.ff.core.common.exceptions;

import com.sparktechcode.springjpasearch.exceptions.BaseSparkException;
import org.springframework.http.HttpStatus;

public class ConflictExistsException extends BaseSparkException {

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.CONFLICT;

    public ConflictExistsException() {
        super(DEFAULT_STATUS, FFError.CONFLICT, "Conflict");
    }

    public ConflictExistsException(String message) {
        super(DEFAULT_STATUS, FFError.CONFLICT, message);
    }

    public ConflictExistsException(FFError error, String message) {
        super(DEFAULT_STATUS, error, message);
    }

    public ConflictExistsException(FFError error, String message, Object data) {
        super(DEFAULT_STATUS, error, message);
        setData(data);
    }
}

