package com.crossover.trial.properties.protocolhandler;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;


/**
 * This class is used to register with JVM wise uri protocol handler. This class is being used to
 * recognized "classpath" as a stream protocol
 * 
 * @see java.net.URLStreamHandlerFactory
 */
public class CrossoverStreamHandlerFactory implements URLStreamHandlerFactory {

  private final Map<String, URLStreamHandler> protocolHandlers;

  public CrossoverStreamHandlerFactory(String protocol, URLStreamHandler urlHandler) {
    protocolHandlers = new HashMap<String, URLStreamHandler>();

    addHandler(protocol, urlHandler);
  }

  public void addHandler(String protocol, URLStreamHandler urlHandler) {
    protocolHandlers.put(protocol, urlHandler);
  }

  public URLStreamHandler createURLStreamHandler(String protocol) {
    return protocolHandlers.get(protocol);
  }
}
