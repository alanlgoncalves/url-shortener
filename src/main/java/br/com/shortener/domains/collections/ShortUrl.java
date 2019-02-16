package br.com.shortener.domains.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "shortUrls")
public class ShortUrl {

  @Id private String id;

  private String url;

  public ShortUrl() {}

  public ShortUrl(final String id, final String url) {
    this.id = id;
    this.url = url;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShortUrl shortUrl = (ShortUrl) o;
    return Objects.equals(id, shortUrl.id) && Objects.equals(url, shortUrl.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, url);
  }
}
