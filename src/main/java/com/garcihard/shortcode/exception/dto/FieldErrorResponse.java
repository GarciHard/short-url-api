package com.garcihard.shortcode.exception.dto;

public record FieldErrorResponse(
        String field,
        String message
) {
}
