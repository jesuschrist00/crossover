package com.crossover.trial.properties.protocolhandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;


/**
 * Stream handler to support to load property files from classpath 
 * 
 * @see java.net.URLStreamHandler
 */
public class ClassPathStreamHandler extends URLStreamHandler {

  private final ClassLoader classLoader;

  public ClassPathStreamHandler() {
    this.classLoader = getClass().getClassLoader();
  }

  public ClassPathStreamHandler(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  protected URLConnection openConnection(URL u) throws IOException {
    final URL resourceUrl = classLoader.getResource(u.getPath());
    return resourceUrl.openConnection();
  }
}
