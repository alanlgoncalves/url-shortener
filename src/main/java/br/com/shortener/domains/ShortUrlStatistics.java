package br.com.shortener.domains;

import br.com.shortener.domains.collections.ShortUrl;
import br.com.shortener.domains.collections.ShortUrlRequest;

import java.util.List;

public class ShortUrlStatistics {

  private ShortUrl shortUrl;

  private long numberOfRequests;

  private List<ShortUrlRequest> lastTenRequests;

  public ShortUrlStatistics(
      final ShortUrl shortUrl,
      final long numberOfRequests,
      final List<ShortUrlRequest> lastTenRequests) {
    this.shortUrl = shortUrl;
    this.numberOfRequests = numberOfRequests;
    this.lastTenRequests = lastTenRequests;
  }

  public ShortUrl getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(final ShortUrl shortUrl) {
    this.shortUrl = shortUrl;
  }

  public long getNumberOfRequests() {
    return numberOfRequests;
  }

  public void setNumberOfRequests(long numberOfRequests) {
    this.numberOfRequests = numberOfRequests;
  }

  public List<ShortUrlRequest> getLastTenRequests() {
    return lastTenRequests;
  }

  public void setLastTenRequests(final List<ShortUrlRequest> lastTenRequests) {
    this.lastTenRequests = lastTenRequests;
  }
}
