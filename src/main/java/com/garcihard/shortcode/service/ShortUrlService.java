package com.garcihard.shortcode.service;

import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;

public interface ShortUrlService {

    ShortUrlResponseDTO createShortUrl(ShortUrlRequestDTO dto);

    void redirectToURl(String url);
}
