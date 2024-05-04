package com.zain.robot.frontend.advice;

import com.zain.robot.frontend.exception.NoResponseFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class RobotFrontendExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoResponseFoundException.class})
    public ResponseEntity<Object> handleNoResponseFoundException(final NoResponseFoundException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }

    @ExceptionHandler({ClassCastException.class})
    public ResponseEntity<Object> handleClassCastException(final ClassCastException ex, final WebRequest request) {
        final String message = "Exception thrown during class cast";
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({IOException.class})
    public ResponseEntity<Object> handleIOException(final IOException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternalServerException(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final String message = "There is some issue with the server. We will resolve it shortly!";
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
