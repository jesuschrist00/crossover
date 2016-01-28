package com.crossover.trial.properties.typedetector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * Detect Jdbc Types
 *
 */
public class JdbcTypeDetector extends TypeDetector {

  private List<String> jdbTypes =
      Arrays.asList("oracle.jdbc.driver.OracleDriver", "com.mysql.jdbc.Driver");

  public Optional<Object> toObject(String value) {

    if (jdbTypes.indexOf(value) != -1) {

      try {
        return Optional.of(Class.forName(value).newInstance());
      } catch (Throwable e) {
      }
    }
    return invokeNext(value);

  }
}

