package br.com.shortener.gateways.http.json.response.builders;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.gateways.http.json.response.ShortUrlRequestResponseJson;
import br.com.shortener.gateways.http.json.response.ShortUrlStatisticsResponseJson;

import java.util.List;

public class ShortUrlStatisticsResponseJsonBuilder {
  private ShortUrl shortUrl;
  private long numberOfRequests;
  private ShortUrlRequestResponseJson lastRequest;
  private List<ShortUrlRequestResponseJson> lastTenRequests;

  public ShortUrlStatisticsResponseJsonBuilder setShortUrl(final ShortUrl shortUrl) {
    this.shortUrl = shortUrl;
    return this;
  }

  public ShortUrlStatisticsResponseJsonBuilder setNumberOfRequests(final long numberOfRequests) {
    this.numberOfRequests = numberOfRequests;
    return this;
  }

  public ShortUrlStatisticsResponseJsonBuilder setLastRequestDateTime(
      final ShortUrlRequestResponseJson lastRequest) {
    this.lastRequest = lastRequest;
    return this;
  }

  public ShortUrlStatisticsResponseJsonBuilder setLastTenRequests(
      final List<ShortUrlRequestResponseJson> lastTenRequests) {
    this.lastTenRequests = lastTenRequests;
    return this;
  }

  public ShortUrlStatisticsResponseJson createShortUrlStatisticsResponseJson() {
    return new ShortUrlStatisticsResponseJson(
        shortUrl, numberOfRequests, lastRequest, lastTenRequests);
  }
}
