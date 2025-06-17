package com.garcihard.shortcode.utils;

public record ShortUrlEncoder(int base, String dictionary) {

    public static final ShortUrlEncoder BASE_62 = new ShortUrlEncoder(62, "0123456789ABCEDFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");

    public String encodeUrl(long longUrlHash) {
        StringBuilder stringBuilder = new StringBuilder(1);

        do {
            stringBuilder.insert(0, dictionary.charAt((int) longUrlHash % base));
            longUrlHash /= base;
        } while (longUrlHash > 0);

        return stringBuilder.toString();
    }
}
