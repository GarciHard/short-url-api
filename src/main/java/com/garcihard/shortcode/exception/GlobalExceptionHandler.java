package com.garcihard.shortcode.exception;

import com.garcihard.shortcode.exception.dto.ErrorResponse;
import com.garcihard.shortcode.utils.ShortUrlConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleUncaughtExceptions(Exception ex) {

        final String errorId = UUID.randomUUID().toString();
        log.error("Unexpected System Error! Error ID: {}", errorId, ex);

        String userMessage = String.format("An unexpected error occurred. Please report this issue with the following ID: %s", errorId);
        ErrorResponse errorResponse =
                ErrorResponse.of(ShortUrlConstants.UNEXPECTED_SERVER_ERROR, userMessage, null);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}