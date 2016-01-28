package com.crossover.trial.properties.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.crossover.trial.properties.PropertyTypeDetector;
import com.crossover.trial.properties.exception.UnknownProtocolException;


/**
 * Base abstract class for property loader
 *
 */
public abstract class FileParser {

  protected final Logger logger = Logger.getLogger(getClass().getName());



  // Load and parse given peoperty for given URI
  public Map<String,Object> loadProperties(String uri) {

    URL url=null;
    try {
      
      url = new URL(uri);
      
      Map<String,Object> properties = loadFile(url.openStream());

      Map<String, Object> propertiesMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

      properties.forEach((k, v) -> {

        Optional<Object> objectValue = PropertyTypeDetector.getInstance().toObject(v.toString());

        propertiesMap.put(k.toString(), objectValue.isPresent()?objectValue.get():null);

      });


      return propertiesMap;
    }
    catch (NullPointerException ex) {
      logger.log(Level.SEVERE, String.format("Property File not found %s", uri));
      throw new UnknownProtocolException(
          String.format("Property File not found %s", uri.toString()));
    }

    catch (MalformedURLException ex) {
      logger.log(Level.SEVERE, String.format("Invalid URL Format %s", uri));
      throw new UnknownProtocolException(String.format("Unsupported URL %s", uri));
    } catch (IOException ex) {
      logger.log(Level.SEVERE, String.format("IO Error for  %s ", uri));
      throw new UnknownProtocolException(String.format("IO Error %s", uri.toString()));

    }


  }

  protected abstract Map<String,Object> loadFile(InputStream inputStream) throws IOException;

}
