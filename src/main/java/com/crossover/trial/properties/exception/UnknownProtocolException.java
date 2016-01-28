package com.crossover.trial.properties.exception;


/**
 * Exception is to be thrown if STREAM protocol is unknown.
 *
 */
public class UnknownProtocolException extends RuntimeException {

  public UnknownProtocolException() {}

  public UnknownProtocolException(String message) {
    super(message);
  }

  public UnknownProtocolException(String message, Throwable cause) {
    super(message, cause);
  }


  public UnknownProtocolException(Throwable cause) {
    super(cause);
  }
}
