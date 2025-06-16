package com.garcihard.shortcode.controller;

import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;
import com.garcihard.shortcode.service.ShortUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(ShortUrlController.SHORT_URL_URI)
@RestController
public class ShortUrlController {

    static final String SHORT_URL_URI = "/api/v1/urls";

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ResponseEntity<ShortUrlResponseDTO> createShorUri(@RequestBody @Valid
                                               ShortUrlRequestDTO requestDto) {
        shortUrlService.createShortUrl(requestDto);

        return ResponseEntity.ok().build();
    }
}
