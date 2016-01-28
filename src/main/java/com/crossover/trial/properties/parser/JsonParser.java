package com.crossover.trial.properties.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;

/*
 * Load and parse json files from given URL
 */
public class JsonParser extends FileParser{

  private Gson gson=new Gson();
  
  @SuppressWarnings("unchecked")
  @Override
  protected Map<String,Object> loadFile(InputStream inputStream) throws IOException {

      return  gson.fromJson(new InputStreamReader(inputStream,"UTF-8"), Map.class);

   }

}
