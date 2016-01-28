package com.crossover.trial.properties;

import java.util.Optional;

import com.crossover.trial.properties.typedetector.TypeDetector;



/**
 * This class detects Property Type for given key and value
 *
 */
public class PropertyTypeDetector {

  private  static PropertyTypeDetector INSTANCE = new PropertyTypeDetector();

  /**
   * Property TypeDetectpr
   */
  private TypeDetector typeDetector;

  
  private PropertyTypeDetector() {}

  public static PropertyTypeDetector getInstance() {

    return INSTANCE;
  }

 


  /**
   * Register Property Type Detector
   * 
   * @param typeDetector PRoperty Type detector . eg. FloatTypeDetector ...
   */
  public void addTypeDetector(TypeDetector typeDetector) {

    if (this.typeDetector == null) {
      this.typeDetector = typeDetector;
    } else {

      TypeDetector _typeDetector = this.typeDetector;
      while ((_typeDetector.getNextTypeDetector()) != null) {
        _typeDetector = _typeDetector.getNextTypeDetector();
      }
      _typeDetector.setNextTypeDetector(typeDetector);

    }

  }

  /**
   * Detects and Print for given property key /value
   * 
   * @param key
   * @param value
   */
  public Optional<Object> toObject(String value) {

    return typeDetector.toObject(value);
  }


}


