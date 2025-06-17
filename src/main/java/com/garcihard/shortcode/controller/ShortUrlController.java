package com.garcihard.shortcode.controller;

import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;
import com.garcihard.shortcode.service.ShortUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping(ShortUrlController.SHORT_URL_URI)
@RestController
public class ShortUrlController {

    static final String SHORT_URL_URI = "/api/v1/urls";

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ResponseEntity<ShortUrlResponseDTO> createShorUri(@RequestBody @Valid
                                               final ShortUrlRequestDTO requestDto) {
        final ShortUrlResponseDTO response = shortUrlService.createShortUrl(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToUrl(@PathVariable String shortCode) {
        final URI redirectionUrl = shortUrlService.getRedirectionUrl(shortCode);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(redirectionUrl)
                .build();
    }
}
