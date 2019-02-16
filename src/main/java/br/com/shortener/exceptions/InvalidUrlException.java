package br.com.shortener.exceptions;

public class InvalidUrlException extends RuntimeException {

  public InvalidUrlException(final String message) {
    super(message);
  }
}
