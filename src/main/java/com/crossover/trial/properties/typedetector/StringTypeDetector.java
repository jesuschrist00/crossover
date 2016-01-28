package com.crossover.trial.properties.typedetector;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

/**
 * String type Detector
 *
 */
public class StringTypeDetector extends TypeDetector {

  public Optional<Object> toObject(String value) {

    if (!StringUtils.isEmpty(value)) {
      return Optional.of(value);
    }

    return invokeNext(value);

  }
}
