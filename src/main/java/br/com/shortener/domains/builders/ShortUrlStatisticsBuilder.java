package br.com.shortener.domains.builders;

import br.com.shortener.domains.ShortUrlStatistics;
import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;

import java.util.List;

public class ShortUrlStatisticsBuilder {
  private ShortUrl shortUrl;
  private long numberOfRequests;
  private List<ShortUrlRequest> lastTenRequests;

  public ShortUrlStatisticsBuilder setShortUrl(ShortUrl shortUrl) {
    this.shortUrl = shortUrl;
    return this;
  }

  public ShortUrlStatisticsBuilder setNumberOfRequests(long numberOfRequests) {
    this.numberOfRequests = numberOfRequests;
    return this;
  }

  public ShortUrlStatisticsBuilder setLastTenRequests(List<ShortUrlRequest> lastTenRequests) {
    this.lastTenRequests = lastTenRequests;
    return this;
  }

  public ShortUrlStatistics createShortUrlStatistics() {
    return new ShortUrlStatistics(shortUrl, numberOfRequests, lastTenRequests);
  }
}
