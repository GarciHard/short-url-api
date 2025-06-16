package com.garcihard.shortcode.exception.custom;

public class ShortUrlNotFoundException extends RuntimeException {
  static final String NOT_FOUND = "URL Not Found with Short Code: ";

  public ShortUrlNotFoundException(String shortCode) {
    super(NOT_FOUND + shortCode);
  }
}
