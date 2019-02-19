package br.com.shortener.gateways.http.json.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel(value = "ShortUrlRequestJson", description = "Contains the URL that will be shorten")
public class ShortUrlRequestJson {

  @ApiModelProperty(value = "The URL that will be shorten", dataType = "String")
  @NotBlank
  @Pattern(
      regexp =
          "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "ShortUrlRequestJson{" + "url='" + url + '\'' + '}';
  }
}
