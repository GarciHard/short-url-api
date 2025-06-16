package com.garcihard.shortcode.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ShortUrlRequestDTO(
        @NotBlank(message = "URI cannot be empty.")
        @Pattern(regexp = URI_REGEX, message = "Invalid URI Format.")
        String longUrl
) {
        private static final String URI_REGEX = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";
}
