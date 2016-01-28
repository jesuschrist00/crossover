package com.crossover.trial.properties;

/**
 * 
 * Define supported File Types
 *
 */
public enum UriSuffix {

  PROPERTIES("PROPERTIES"), JSON("JSON");

  private String suffix;

  private UriSuffix(String suffix) {
    this.suffix = suffix;
  }

  public String getSuffix() {
    return suffix;
  }

}
