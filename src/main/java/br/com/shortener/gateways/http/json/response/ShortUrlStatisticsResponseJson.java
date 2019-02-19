package br.com.shortener.gateways.http.json.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(
    value = "ShortUrlStatisticsResponseJson",
    description = "Contains the Short URL statistics")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortUrlStatisticsResponseJson {

  @ApiModelProperty(value = "The Short URL information", dataType = "ShortUrlJson")
  private ShortUrlJson shortUrl;

  @ApiModelProperty(value = "The Short URL number of requests", dataType = "Long")
  private long numberOfRequests;

  @ApiModelProperty(
      value = "The Short URL last request information",
      dataType = "ShortUrlRequestResponseJson")
  private ShortUrlRequestResponseJson lastRequest;

  @ApiModelProperty(value = "The Short URL last requests list information", dataType = "List")
  private List<ShortUrlRequestResponseJson> lastRequests;

  public ShortUrlStatisticsResponseJson() {}

  public ShortUrlStatisticsResponseJson(
      final ShortUrlJson shortUrl,
      final long numberOfRequests,
      final ShortUrlRequestResponseJson lastRequest,
      final List<ShortUrlRequestResponseJson> lastRequests) {
    this.shortUrl = shortUrl;
    this.numberOfRequests = numberOfRequests;
    this.lastRequest = lastRequest;
    this.lastRequests = lastRequests;
  }

  public ShortUrlJson getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(final ShortUrlJson shortUrl) {
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
