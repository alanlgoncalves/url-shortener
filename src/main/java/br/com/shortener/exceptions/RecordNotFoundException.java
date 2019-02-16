package br.com.shortener.exceptions;

public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(final String message) {
    super(message);
  }
}
