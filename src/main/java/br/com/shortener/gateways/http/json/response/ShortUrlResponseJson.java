package br.com.shortener.gateways.http.json.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ShortUrlResponseJson", description = "Contains the Short URL that was created")
public class ShortUrlResponseJson {

  @ApiModelProperty(value = "The short URL that was created", dataType = "String")
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
