package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiException handleApiRequestException(BadRequestException e) {
        ApiException apiE = new ApiException(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return apiE;
    };

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiException handleNotFoundException(NotFoundException e) {
        ApiException apiE = new ApiException(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return apiE;
    }
}
