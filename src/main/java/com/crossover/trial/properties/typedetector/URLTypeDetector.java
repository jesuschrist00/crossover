package com.crossover.trial.properties.typedetector;

import java.net.URL;
import java.util.Optional;

public class URLTypeDetector extends TypeDetector {

  public Optional<Object> toObject(String value) {
    try {
      return Optional.of(new URL(value));
    } catch (Exception e) {

    }
    return nextTypeDetector.toObject(value);
  }

}

