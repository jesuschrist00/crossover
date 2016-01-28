package com.crossover.trial.properties.typedetector;

import java.util.Optional;

import org.apache.commons.validator.routines.DoubleValidator;


/**
 * Detects Integer Type
 *
 */
public class DoubleTypeDetector extends TypeDetector {

  public Optional<Object> toObject(String value) {

    boolean isValid = DoubleValidator.getInstance().isValid(value);
    if (isValid) {
      return Optional.of(Integer.parseInt(value));
    }

    return invokeNext(value);

  }
}
