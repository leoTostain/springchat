package com.leocorp.springchat;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class AppControllerAdvice {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AppControllerAdvice.class);

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpServletRequest request) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Error");
        problemDetail.setDetail("Got an error while validating the request, see the error description");
        problemDetail.setProperty("error", ex.getDetailMessageArguments());

        log.warn(ex.getParameterValidationResults().toString());
        return problemDetail;
    }

    @ExceptionHandler(MethodValidationException.class)
    public ProblemDetail handleMethodValidationException(MethodValidationException ex, HttpServletRequest request) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Error");
        problemDetail.setDetail("Got an error while validating the request, see the error description");
        problemDetail.setProperty("error", ex.getMessage());

        log.warn(ex.getParameterValidationResults().toString());
        return problemDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Credential Validation Error");
        problemDetail.setDetail("Bad credentials");
        problemDetail.setProperty("error", ex.getMessage());

        log.warn(ex.getLocalizedMessage());
        return problemDetail;
    }
}
