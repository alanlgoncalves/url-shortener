package br.com.shortener.gateways.http.json.response;

public class ShortUrlResponseJson {

  private String shortUrl;

  public ShortUrlResponseJson() {}

  public ShortUrlResponseJson(final String url) {
    this.shortUrl = url;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(final String shortUrl) {
    this.shortUrl = shortUrl;
  }
}
