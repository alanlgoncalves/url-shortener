package br.com.shortener.gateways.http.json.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class ShortUrlRequestResponseJson {
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
