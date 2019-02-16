package br.com.shortener.domains.builders;

import br.com.shortener.domains.collections.ShortUrl;

public class ShortUrlBuilder {
  private String id;
  private String url;

  public ShortUrlBuilder setId(final String id) {
    this.id = id;
    return this;
  }

  public ShortUrlBuilder setUrl(final String url) {
    this.url = url;
    return this;
  }

  public ShortUrl createShortUrl() {
    return new ShortUrl(id, url);
  }
}
