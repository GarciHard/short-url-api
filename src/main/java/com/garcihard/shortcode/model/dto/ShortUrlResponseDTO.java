package com.garcihard.shortcode.model.dto;

public record ShortUrlResponseDTO(
        String longUrl,
        String shortCode
) {

    public static ShortUrlResponseDTO of(String longUrl, String shortCode) {
        return new ShortUrlResponseDTO(longUrl, shortCode);
    }
}
