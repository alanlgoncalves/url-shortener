package br.com.shortener.gateways.http.json.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ShortUrlJson", description = "Contains information about Short URL")
public class ShortUrlJson {

  @ApiModelProperty(value = "The real URL that belongs to Short URL", dataType = "String")
  private String url;

  public ShortUrlJson() {}

  public ShortUrlJson(final String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }
}
