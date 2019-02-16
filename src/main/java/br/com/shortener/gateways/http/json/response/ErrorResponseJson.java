package br.com.shortener.gateways.http.json.response;

public class ErrorResponseJson {

  public ErrorResponseJson(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  private String message;
}
