package com.crossover.trial.properties.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.crossover.trial.properties.UriSuffix;


/**
 * Default ProperyFileLoaderFactory
 * 
 * @see PropertyFileLoaderFactory
 */
public class FileParserFactory  {

  private static Map<UriSuffix, FileParser> fileParsersMap = new HashMap<>();

  protected Logger logger = Logger.getLogger(getClass().getName());


  /**
   * Create File Parser for given uri suffix
   * @param suffix uri suffix
   * @throws NullPointerException if given suffix is null 
   * @return
   */

  public static FileParser createFileParser(UriSuffix suffix) {
    
    if(suffix==null)
      throw new NullPointerException("uri suffix is null");
  
      return fileParsersMap.get(suffix);
  }

  /**
   * Register FileParser for given uri suffix
   * @param suffix URI suffix to be registered
   * @param fileParser File PArser to be registered
   * @throws NullPointerException if suffix or fileParser is null
   */

  public synchronized static void registerFileParser(UriSuffix suffix, FileParser parser) {
    if(suffix==null)
      throw new NullPointerException("uri suffix is null");
    
    if(parser==null)
      throw new NullPointerException("fileParser is null");
  
      fileParsersMap.putIfAbsent(suffix, parser);
      
  }

}
