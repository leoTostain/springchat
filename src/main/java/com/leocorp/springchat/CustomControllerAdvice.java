package com.leocorp.springchat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(value = HandlerMethodValidationException.class)
    protected ProblemDetail handleValidationError(HandlerMethodValidationException ex) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation error");
        problem.setDetail("One or multiples fields are invalid");
        problem.setProperty("error", ex.getDetailMessageArguments());
        return problem;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ProblemDetail handleNotValidError(MethodArgumentNotValidException ex) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation error");
        problem.setDetail("A field is invalid");
        problem.setProperty("error", ex.getBindingResult().getAllErrors());
        return problem;
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ProblemDetail handleNotReadableError(HttpMessageNotReadableException ex) {
        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        if (ex.getMessage().startsWith("Required request body is missing")) {
            problem.setTitle("Validation error");
            problem.setDetail("Required request body is missing");
        } else {
            problem.setDetail("An error occurred while handling the request");
        }
        return problem;
    }
}
