package com.garcihard.shortcode.exception;

import com.garcihard.shortcode.exception.custom.ShortUrlNotFoundException;
import com.garcihard.shortcode.exception.dto.ErrorResponse;
import com.garcihard.shortcode.exception.dto.FieldErrorResponse;
import com.garcihard.shortcode.utils.ShortUrlConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@ControllerAdvice
public class ShortUrlExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream().map(error -> new FieldErrorResponse(
                        error.getField(),
                        error.getDefaultMessage()
                )).toList();

        ErrorResponse errorResponse = ErrorResponse.of(ShortUrlConstants.VALIDATION_ERROR,
                ShortUrlConstants.VALIDATION_FAILED_MSG,
                fieldErrors);

        log.warn(ShortUrlConstants.VALIDATION_FAILED_MSG,
                kv("details", fieldErrors)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleShortUrlNotFoundException(ShortUrlNotFoundException ex) {
        ErrorResponse errorResponse =
                ErrorResponse.of(ShortUrlConstants.RESOURCE_NOT_FOUND, ex.getMessage(), null);

        log.warn("Client error: {}, Error Code: {}",
                ex.getMessage(), ShortUrlConstants.RESOURCE_NOT_FOUND);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
