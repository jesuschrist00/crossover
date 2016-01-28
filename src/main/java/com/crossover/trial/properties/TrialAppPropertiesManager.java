package com.crossover.trial.properties;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.crossover.trial.properties.exception.UnknownProtocolException;
import com.crossover.trial.properties.parser.FileParser;
import com.crossover.trial.properties.parser.FileParserFactory;

/**
 * A simple main method to load and print properties. You should feel free to change this class or
 * to create additional class. You may add addtional methods, but must implement the
 * AppPropertiesManager API contract.
 * 
 * Note: a default constructor is required
 *
 * @author code test administrator
 */
public class TrialAppPropertiesManager implements AppPropertiesManager {

  protected Logger logger = Logger.getLogger(getClass().getName());


  private Map<String, Object> parseFile(String uri) {
   
    try{
      FileParser parser = FileParserFactory.createFileParser(createUriPrefix(uri));
      return parser.loadProperties(uri);
    }
    catch ( UnknownProtocolException ex) {
      logger.log(Level.SEVERE, ex.getMessage()+" Ignoring file "+uri);
    }
    return Collections.emptyMap();
  }

  
  
  
  @Override
  public AppProperties loadProps(List<String> propUris) {

    if(propUris==null)
      throw new IllegalArgumentException("File Uris cant not be null");
    
    Map <String,Object>propertiesMap=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    
    propUris.parallelStream().map(uri -> parseFile(uri)).flatMap(m -> m.entrySet().stream())
            .forEach(entry->propertiesMap.put(entry.getKey(), entry.getValue()));
    
    
    return new TrialAppProperties(propertiesMap);

  }

  
  
  @Override
  public void printProperties(AppProperties props, PrintStream sync) {
    props.getKnownProperties().stream()
        .sorted((key1, key2) -> key1.toString().compareTo(key2.toString())).forEach(key -> {

          Object value = props.get(key.toString());

          sync.println(String.format("%s, %s, %s", key, value.getClass().getName(), value));
        });
  }
  
  

  private UriSuffix createUriPrefix(String uri) {
    int index = uri.lastIndexOf(".");
    if (index != -1) {
      String suffix = uri.substring(index + 1);
      return UriSuffix.valueOf(suffix.toUpperCase());
    }
    return null;
  }

}
