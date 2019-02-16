package br.com.shortener.domains.builder;

import br.com.shortener.domains.ShortUrl;

public class ShortUrlBuilder {
  private String id;
  private String url;

  public ShortUrlBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public ShortUrlBuilder setUrl(String url) {
    this.url = url;
    return this;
  }

  public ShortUrl createShortUrl() {
    return new ShortUrl(id, url);
  }
}
