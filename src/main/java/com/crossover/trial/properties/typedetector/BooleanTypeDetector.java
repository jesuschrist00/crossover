package com.crossover.trial.properties.typedetector;

import java.util.Optional;

/**
 * Detect Boolean Type
 *
 */
public class BooleanTypeDetector extends TypeDetector {

  public Optional<Object> toObject(String value) {

    if (value.equalsIgnoreCase(Boolean.TRUE.toString())
        || value.equalsIgnoreCase(Boolean.FALSE.toString())) {
      return Optional.of(Boolean.parseBoolean(value));
    }
    return invokeNext(value);
  }
}

