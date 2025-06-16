package com.garcihard.shortcode.model.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;


@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "url_mappings")
public class ShortUrlEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String longUrl;

    String shortCode;

}
