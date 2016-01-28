package com.crossover.trial.properties;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.crossover.trial.properties.parser.FileParserFactory;
import com.crossover.trial.properties.parser.JsonParser;
import com.crossover.trial.properties.parser.PropertiesFileParser;
import com.crossover.trial.properties.protocolhandler.ClassPathStreamHandler;
import com.crossover.trial.properties.protocolhandler.CrossoverStreamHandlerFactory;
import com.crossover.trial.properties.typedetector.AwsRegionDetector;
import com.crossover.trial.properties.typedetector.BooleanTypeDetector;
import com.crossover.trial.properties.typedetector.DoubleTypeDetector;
import com.crossover.trial.properties.typedetector.FloatTypeDetector;
import com.crossover.trial.properties.typedetector.IntegerTypeDetector;
import com.crossover.trial.properties.typedetector.JdbcTypeDetector;
import com.crossover.trial.properties.typedetector.StringTypeDetector;
import com.crossover.trial.properties.typedetector.URLTypeDetector;



/**
 * Provides example usage of the API and classes - although candidates can use this
 * Main method to test if their changes will be accepted by the autograder. If your
 * solution is incompatible with this main method, it will be incompatible with the
 * autograder and may cause your solution to be failed.
 * 
 * @author code test administrator
 */
public class Main {
    /**
     * Main method useful for your testing, this method is not tested by the grader.
     *
     * @param args
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void main(String[] args) throws URISyntaxException, IOException {

         // Register classpath as a protocol
        URL.setURLStreamHandlerFactory(new CrossoverStreamHandlerFactory("classpath", new ClassPathStreamHandler()));


        // Register PRoperty Types
        registerPropertyTypes();

        // Create and initialize File Parser  factory
        FileParserFactory.registerFileParser(UriSuffix.JSON, new JsonParser());
        
        FileParserFactory.registerFileParser(UriSuffix.PROPERTIES, new PropertiesFileParser());

        
        List<String> propertySourceUris = Arrays.asList(args);

        // invoke the property parser and print out properties alphabetically
        AppPropertiesManager m = new TrialAppPropertiesManager();
        AppProperties props = m.loadProps(propertySourceUris);
        m.printProperties(props, System.out);
    }
    

    /*
     * Register PRoperty types
     */
    private static void registerPropertyTypes() {

      PropertyTypeDetector typeDetector = PropertyTypeDetector.getInstance();
      typeDetector.addTypeDetector(new AwsRegionDetector());

      typeDetector.addTypeDetector(new JdbcTypeDetector());
      typeDetector.addTypeDetector(new IntegerTypeDetector());
      typeDetector.addTypeDetector(new FloatTypeDetector());
      typeDetector.addTypeDetector(new DoubleTypeDetector());
      typeDetector.addTypeDetector(new URLTypeDetector());
      typeDetector.addTypeDetector(new BooleanTypeDetector());
      typeDetector.addTypeDetector(new StringTypeDetector());


    }
    
}
