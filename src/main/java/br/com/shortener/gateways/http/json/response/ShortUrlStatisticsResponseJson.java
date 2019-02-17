package br.com.shortener.gateways.http.json.response;

import br.com.shortener.domains.collections.ShortUrl;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortUrlStatisticsResponseJson {

  private ShortUrl shortUrl;

  private long numberOfRequests;

  private ShortUrlRequestResponseJson lastRequest;

  private List<ShortUrlRequestResponseJson> lastRequests;

  public ShortUrlStatisticsResponseJson() {}

  public ShortUrlStatisticsResponseJson(
      final ShortUrl shortUrl,
      final long numberOfRequests,
      final ShortUrlRequestResponseJson lastRequest,
      final List<ShortUrlRequestResponseJson> lastRequests) {
    this.shortUrl = shortUrl;
    this.numberOfRequests = numberOfRequests;
    this.lastRequest = lastRequest;
    this.lastRequests = lastRequests;
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

  public void setNumberOfRequests(final long numberOfRequests) {
    this.numberOfRequests = numberOfRequests;
  }

  public ShortUrlRequestResponseJson getLastRequest() {
    return lastRequest;
  }

  public void setLastRequest(final ShortUrlRequestResponseJson lastRequest) {
    this.lastRequest = lastRequest;
  }

  public List<ShortUrlRequestResponseJson> getLastRequests() {
    return lastRequests;
  }

  public void setLastRequests(final List<ShortUrlRequestResponseJson> lastRequests) {
    this.lastRequests = lastRequests;
  }
}
