package com.garcihard.shortcode.service;

import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;

import java.net.URI;

public interface ShortUrlService {

    ShortUrlResponseDTO createShortUrl(ShortUrlRequestDTO request);

    URI getRedirectionUrl(String shortUrl);
}
