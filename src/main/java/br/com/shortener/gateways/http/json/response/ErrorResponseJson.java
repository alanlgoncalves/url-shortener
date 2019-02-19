package br.com.shortener.gateways.http.json.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
    value = "ErrorResponseJson",
    description = "Contains the message about an error that occurs")
public class ErrorResponseJson {

  @ApiModelProperty(value = "The error message", dataType = "String")
  private String message;

  public ErrorResponseJson(final String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
