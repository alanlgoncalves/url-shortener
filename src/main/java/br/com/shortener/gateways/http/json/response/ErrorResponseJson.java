package br.com.shortener.gateways.http.json.response;

public class ErrorResponseJson {

  public ErrorResponseJson(final String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  private String message;
}
