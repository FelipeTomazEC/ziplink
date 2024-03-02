package com.github.felipetomazec.ziplink.api.errorhandling;

import com.github.felipetomazec.ziplink.usecases.accessoriginalurl.InvalidShortURLException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse error = new ErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.addError(String.format("Field %s is invalid. %s", fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return error;
    }

    @ExceptionHandler(InvalidShortURLException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse onInvalidShortURLException(InvalidShortURLException e) {
        ErrorResponse error = new ErrorResponse();
        error.addError(e.getLocalizedMessage());

        return error;
    }
}
