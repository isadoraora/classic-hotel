package com.classichotel.exception;

public class NotFoundException extends DomainException {
  public NotFoundException(String message) {
    super(message);
  }
}
