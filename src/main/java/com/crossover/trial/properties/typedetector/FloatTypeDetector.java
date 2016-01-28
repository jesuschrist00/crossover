package com.crossover.trial.properties.typedetector;

import java.util.Optional;

import org.apache.commons.validator.routines.FloatValidator;


/**
 * Detects Float Type
 *
 */
public class FloatTypeDetector extends TypeDetector {

  public Optional<Object> toObject(String value) {

    boolean isValid = FloatValidator.getInstance().isValid(value);
    if (isValid) {
      return Optional.of(Float.parseFloat(value));
    }

    return invokeNext(value);
  }
}
