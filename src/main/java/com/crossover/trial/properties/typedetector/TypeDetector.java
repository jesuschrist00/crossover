package com.crossover.trial.properties.typedetector;

import java.util.Optional;
import java.util.logging.Logger;


/**
 * Interface for Type Detector
 *
 */
public abstract class TypeDetector {

  protected TypeDetector nextTypeDetector;

  protected final Logger logger = Logger.getLogger(this.getClass().getName());


  public TypeDetector getNextTypeDetector() {
    return nextTypeDetector;
  }

  public void setNextTypeDetector(TypeDetector nextTypeDetector) {
    this.nextTypeDetector = nextTypeDetector;
  }

  public abstract Optional<Object> toObject(String value);

  protected Optional<Object> invokeNext(String value) {
    if (nextTypeDetector != null) {
      return nextTypeDetector.toObject(value);
    }
    return Optional.empty();

  }


}

