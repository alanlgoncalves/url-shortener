package br.com.shortener.domains.builders;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;

import java.time.LocalDateTime;

public class ShortUrlRequestBuilder {
  private String id;
  private ShortUrl shortUrl;
  private String requestIp;
  private LocalDateTime requestDateTime;

  public ShortUrlRequestBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public ShortUrlRequestBuilder setShortUrl(ShortUrl shortUrl) {
    this.shortUrl = shortUrl;
    return this;
  }

  public ShortUrlRequestBuilder setRequestIp(String requestIp) {
    this.requestIp = requestIp;
    return this;
  }

  public ShortUrlRequestBuilder setRequestDateTime(LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
    return this;
  }

  public ShortUrlRequest createShortUrlRequest() {
    return new ShortUrlRequest(id, shortUrl, requestIp, requestDateTime);
  }
}
