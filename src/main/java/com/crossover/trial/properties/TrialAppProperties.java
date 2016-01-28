package com.crossover.trial.properties;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * A dummy implementation of TrialAppProperties, this clearly doesn't work. Candidates SHOULD change 
 * this class to add their implementation. You are also free to create additional classes
 *
 * note: a default constructor is required.
 *
 * @author code test administrator
 */
public class TrialAppProperties implements AppProperties {

    private Map<String, Object> propertiesMap;
    
    public TrialAppProperties(Map<String, Object> propertiesMap) {
      this.propertiesMap = propertiesMap;
    }

    public TrialAppProperties() {
      this.propertiesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }
 

    @Override
    public List<String> getMissingProperties() {
      return propertiesMap.entrySet().parallelStream().filter(p -> p.getValue() == null).map(p -> p.getKey())
          .collect(Collectors.toList());
    }

    @Override
    public List<String> getKnownProperties() {
      return propertiesMap.entrySet().parallelStream().filter(p->p.getValue()!=null).map(p -> p.getKey()).collect(Collectors.toList());
    }

    @Override
    public boolean isValid() {
        return getMissingProperties().isEmpty();
    }

    @Override
    public void clear() {
      propertiesMap.clear();
    }

    @Override
    public Object get(String key) {
        return propertiesMap.get(key)
;    }
}

