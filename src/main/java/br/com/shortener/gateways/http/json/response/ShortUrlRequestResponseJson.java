package br.com.shortener.gateways.http.json.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(
    value = "ShortUrlRequestResponseJson",
    description = "Contains last Short URL request date/time information")
public class ShortUrlRequestResponseJson {

  @ApiModelProperty(value = "The last Short URL request date/time", dataType = "LocalDateTime")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime requestDateTime;

  public ShortUrlRequestResponseJson(final LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }

  public LocalDateTime getRequestDateTime() {
    return requestDateTime;
  }

  public void setRequestDateTime(final LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }
}
