package br.com.shortener.gateways.http.json.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class ShortUrlRequestResponseJson {
  private String requestIp;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime requestDateTime;

  public ShortUrlRequestResponseJson(final String requestIp, final LocalDateTime requestDateTime) {
    this.requestIp = requestIp;
    this.requestDateTime = requestDateTime;
  }

  public String getRequestIp() {
    return requestIp;
  }

  public void setRequestIp(final String requestIp) {
    this.requestIp = requestIp;
  }

  public LocalDateTime getRequestDateTime() {
    return requestDateTime;
  }

  public void setRequestDateTime(final LocalDateTime requestDateTime) {
    this.requestDateTime = requestDateTime;
  }
}
