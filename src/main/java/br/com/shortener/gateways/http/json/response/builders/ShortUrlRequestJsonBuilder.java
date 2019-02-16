package br.com.shortener.gateways.http.json.response.builders;

import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;

import java.time.LocalDateTime;

public class ShortUrlRequestJsonBuilder {
  private String requestIp;
  private LocalDateTime requestDateTime;

  public ShortUrlRequestJsonBuilder setRequestIp(final String requestIp) {
    this.requestIp = requestIp;
    return this;
  }

  public ShortUrlRequestJsonBuilder setRequestDateTime(final LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
    return this;
  }

  public ShortUrlRequestResponseJson createShortUrlRequestJson() {
    return new ShortUrlRequestResponseJson(requestIp, requestDateTime);
  }
}
