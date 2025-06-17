package com.garcihard.shortcode.service.impl;

import com.garcihard.shortcode.exception.custom.ShortUrlNotFoundException;
import com.garcihard.shortcode.mapper.ShortUrlMapper;
import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;
import com.garcihard.shortcode.model.entity.ShortUrlEntity;
import com.garcihard.shortcode.repository.ShortUrlRepository;
import com.garcihard.shortcode.service.ShortUrlService;
import com.garcihard.shortcode.utils.ShortUrlEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlMapper mapper;
    private final ShortUrlRepository shortUrlRespository;

    @Override
    @Transactional // Aseguramos que suceda en UNA ÚNICA TRANSACCIÓN.
    public ShortUrlResponseDTO createShortUrl(ShortUrlRequestDTO request) {
        return shortUrlRespository.findByLongUrl(request.longUrl())
                .map(mapper::toDto)
                .orElseGet(() -> {
                   ShortUrlEntity entity = new ShortUrlEntity();
                   entity.setLongUrl(request.longUrl());

                   entity = shortUrlRespository.save(entity);

                   final String shortCode = ShortUrlEncoder.BASE_62.encodeUrl(entity.getId());
                   entity.setShortCode(shortCode);

                   return mapper.toDto(entity);
                });
    }

    @Override
    public URI getRedirectionUrl(String shortUrl) {

        ShortUrlEntity entity = shortUrlRespository.findByShortCode(shortUrl)
                .orElseThrow(() -> new ShortUrlNotFoundException(shortUrl));

        return URI.create(entity.getLongUrl());
    }
}
