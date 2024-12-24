package com.sparktechcode.ff.core.common.config;

import com.sparktechcode.ff.core.common.exceptions.FFError;
import com.sparktechcode.ff.core.common.payloads.ErrorResult;
import com.sparktechcode.springcrud.exceptions.NotFoundException;
import com.sparktechcode.springjpasearch.exceptions.BaseSparkException;
import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static com.sparktechcode.ff.core.common.exceptions.FFError.VALIDATION_FAILED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(value = BaseSparkException.class)
    protected ResponseEntity<ErrorResult<Object>> handleBase(BaseSparkException exception) {
        if (exception instanceof NotFoundException) {
            log.warn(exception.getMessage());
        } else {
            log.error(exception.getMessage(), exception);
        }
        Sentry.captureException(exception);
        var error = ErrorResult.of(exception.getData(), exception.getError(), exception.getMessage());
        return new ResponseEntity<>(error, exception.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResult<List<ObjectError>>> handleBase(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        Sentry.captureException(exception);
        var error = ErrorResult.of(exception.getAllErrors(), VALIDATION_FAILED, exception.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorResult<Object>> handleOther(Exception exception) {
        log.error(exception.getMessage(), exception);
        Sentry.captureException(exception);
        var error = ErrorResult.of(null, FFError.UNKNOWN_ERROR, exception.getMessage());
        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }
}

