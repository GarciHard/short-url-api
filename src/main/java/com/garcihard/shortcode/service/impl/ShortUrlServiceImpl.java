package com.garcihard.shortcode.service.impl;

import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;
import com.garcihard.shortcode.repository.ShortUrlRespository;
import com.garcihard.shortcode.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlRespository shortUrlRespository;

    @Override
    public ShortUrlResponseDTO createShortUrl(ShortUrlRequestDTO dto) {
        return null;
    }

    @Override
    public void redirectToURl(String url) {

    }
}
