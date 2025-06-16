package com.garcihard.shortcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ShortUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }

}
