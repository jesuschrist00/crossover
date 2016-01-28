package com.crossover.trial.properties.typedetector;

import java.util.Optional;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;

/**
 * Detects AWS Regions
 *
 */
public class AwsRegionDetector extends TypeDetector {

  public Optional<Object> toObject(String value) {

    Region region = RegionUtils.getRegion(value);
    if (region != null) {
      return Optional.of(region);
    }

    return invokeNext(value);
  }
}
