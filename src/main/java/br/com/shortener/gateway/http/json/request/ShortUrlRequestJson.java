package br.com.shortener.gateway.http.json.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ShortUrlRequestJson {

  @NotBlank
  @Pattern(
      regexp =
          "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
