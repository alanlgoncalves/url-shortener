package br.com.shortener.gateways.http.json.response.builders;

import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;

import java.time.LocalDateTime;

public class ShortUrlRequestJsonBuilder {
  private LocalDateTime requestDateTime;

  public ShortUrlRequestJsonBuilder setRequestDateTime(final LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
    return this;
  }

  public ShortUrlRequestResponseJson createShortUrlRequestJson() {
    return new ShortUrlRequestResponseJson(requestDateTime);
  }
}
