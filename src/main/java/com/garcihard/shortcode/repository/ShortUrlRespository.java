package com.garcihard.shortcode.repository;

import com.garcihard.shortcode.model.entity.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShortUrlRespository extends JpaRepository<ShortUrlEntity, UUID> {

    ShortUrlEntity getShortUrlEntityByShortCode(String shortCode);
}
