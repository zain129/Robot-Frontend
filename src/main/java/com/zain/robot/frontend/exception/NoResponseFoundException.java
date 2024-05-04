package com.zain.robot.frontend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoResponseFoundException extends RuntimeException {

    public NoResponseFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
