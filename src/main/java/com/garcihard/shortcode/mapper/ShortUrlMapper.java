package com.garcihard.shortcode.mapper;

import com.garcihard.shortcode.model.dto.ShortUrlResponseDTO;
import com.garcihard.shortcode.model.entity.ShortUrlEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ShortUrlMapper {

    ShortUrlResponseDTO toDto(ShortUrlEntity entity);

    ShortUrlEntity toEntity(ShortUrlResponseDTO dto);

}
