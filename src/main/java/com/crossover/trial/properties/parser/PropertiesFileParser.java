package com.crossover.trial.properties.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



/**
 * Load key=value based properties 
 */
public class PropertiesFileParser extends FileParser {

  @Override
  protected Map<String,Object> loadFile(InputStream inputStream) throws IOException {

    Properties properties = new Properties();
  
    properties.load(inputStream);
    
    Map<String,Object>propertiesMap=new HashMap<>();
    
    properties.forEach((k, v) -> {
      propertiesMap.put(k.toString(), v);
    });
    
    return propertiesMap;
    

  }

}
