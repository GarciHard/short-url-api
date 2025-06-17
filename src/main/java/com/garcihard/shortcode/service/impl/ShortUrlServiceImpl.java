package com.garcihard.shortcode.service.impl;

import com.garcihard.shortcode.exception.custom.ShortUrlNotFoundException;
import com.garcihard.shortcode.mapper.ShortUrlMapper;
import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;
import com.garcihard.shortcode.model.entity.ShortUrlEntity;
import com.garcihard.shortcode.repository.ShortUrlRespository;
import com.garcihard.shortcode.service.ShortUrlService;
import com.garcihard.shortcode.utils.ShortUrlEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final ShortUrlMapper mapper;
    private final ShortUrlRespository respository;

    @Override
    public ShortUrlResponseDTO createShortUrl(ShortUrlRequestDTO request) {
        final int urlToHash = Math.abs(request.longUrl().hashCode());
        final String urlEncoded = ShortUrlEncoder.BASE_62.encodeUrl(urlToHash);

        ShortUrlEntity entity = mapper.toEntity(
                ShortUrlResponseDTO.of(request.longUrl(), urlEncoded)
        );

        return  mapper.toDto(respository.save(entity));
    }

    @Override
    public URI getRedirectionUrl(String shortUrl) {

        ShortUrlEntity entity = Optional.ofNullable(
                respository.getShortUrlEntityByShortCode(shortUrl)
        ).orElseThrow(() -> new ShortUrlNotFoundException(shortUrl));

        return URI.create(entity.getLongUrl());
    }
}
