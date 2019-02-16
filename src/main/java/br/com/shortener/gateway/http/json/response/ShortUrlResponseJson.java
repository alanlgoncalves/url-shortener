package br.com.shortener.gateway.http.json.response;

public class ShortUrlResponseJson {

  private String shortUrl;

  public ShortUrlResponseJson(String url) {
    this.shortUrl = url;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }
}
