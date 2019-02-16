package br.com.shortener.domains.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shortUrls")
public class ShortUrl {

  @Id private String id;

  private String url;

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
}
