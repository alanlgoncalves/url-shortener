package br.com.shortener.domains.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "shortUrlStatistics")
@CompoundIndexes(value = {@CompoundIndex(def = "{'shortUrl._id': 1}", background = true)})
public class ShortUrlRequest {

  @Id private String id;

  private ShortUrl shortUrl;

  private String requestIp;

  private LocalDateTime requestDateTime;

  public ShortUrlRequest(
      final String id,
      final ShortUrl shortUrl,
      final String requestIp,
      final LocalDateTime requestDateTime) {
    this.id = id;
    this.shortUrl = shortUrl;
    this.requestIp = requestIp;
    this.requestDateTime = requestDateTime;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public ShortUrl getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(final ShortUrl shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getRequestIp() {
    return requestIp;
  }

  public void setRequestIp(final String requestIp) {
    this.requestIp = requestIp;
  }

  public LocalDateTime getRequestDateTime() {
    return requestDateTime;
  }

  public void setRequestDateTime(final LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }
}
