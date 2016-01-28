package com.crossover.trial.properties.typedetector;

import java.util.Optional;

import org.apache.commons.validator.routines.IntegerValidator;


/**
 * Detects Integer Type
 *
 */
public class IntegerTypeDetector extends TypeDetector {

  public Optional<Object> toObject(String value) {

    boolean isValid = IntegerValidator.getInstance().isValid(value);
    if (isValid) {
      return Optional.of(Integer.parseInt(value));
    }

    return invokeNext(value);
  }
}
