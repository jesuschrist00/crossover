package com.crossover.trial.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.net.URL;
import java.sql.Driver;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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

public class CrossOverPropertiesTest {

	static{
      // Register classpath as a protocol
	  
      URL.setURLStreamHandlerFactory(new CrossoverStreamHandlerFactory("classpath", new ClassPathStreamHandler()));

		
	}
	
	@Before
	public void setUp(){
	
	    //Register PRoperty Type Detectors
		registerPropertyTypes();
		
	    // Create and initialize File Parser  factory
        FileParserFactory.registerFileParser(UriSuffix.JSON, new JsonParser());     
        FileParserFactory.registerFileParser(UriSuffix.PROPERTIES, new PropertiesFileParser());

        

		
	}
	
	/**
	 * Test .properties Parser
	 * @throws Exception
	 */
	@Test
	public void testPropertiesParser() throws Exception{
	     
		String uri = getClass().getClassLoader().getResource("jdbc.properties").toExternalForm();
	   
	    List<String> propertySourceUris = Arrays.asList(uri);

        // invoke the property parser and print out properties alphabetically
        AppPropertiesManager manager = new TrialAppPropertiesManager();
 		
	    AppProperties properties=manager.loadProps(propertySourceUris);
	     

	     assertTrue( properties.getKnownProperties().size() == 7);
	
	}
	
    /**
     * Test .json Parser
     * @throws Exception
     */
    @Test
    public void testJsonParser() throws Exception{
         
        String uri = getClass().getClassLoader().getResource("config.json").toExternalForm();
       
        List<String> propertySourceUris = Arrays.asList(uri);

        // invoke the property parser and print out properties alphabetically
        AppPropertiesManager manager = new TrialAppPropertiesManager();
        
        AppProperties properties=manager.loadProps(propertySourceUris);
         

         assertTrue( properties.getKnownProperties().size() == 7);
    
    }
    	

    /**
     * Test classpath:resource
     * @throws Exception
     */
	@Test
	public void testClassPathProtocol() throws Exception{
	     
		String uri = "classpath:resources/jdbc.properties";
        List<String> propertySourceUris = Arrays.asList(uri);

        // invoke the property parser and print out properties alphabetically
        AppPropertiesManager manager = new TrialAppPropertiesManager();
        
        AppProperties properties=manager.loadProps(propertySourceUris);
         

         assertTrue( properties.getKnownProperties().size() == 7);
	
	}

	/**
	 * Test unrecognized stream protocol
	 * @throws Exception
	 */
	@Test
	public void testUnknowProtocol() throws Exception{
	     
		String uri = "classpath12:resources/jdbc.properties";
		
	    List<String> propertySourceUris = Arrays.asList(uri);
		
	    
	     // invoke the property parser and print out properties alphabetically
        AppPropertiesManager manager = new TrialAppPropertiesManager();
        
        AppProperties properties=manager.loadProps(propertySourceUris);
         

         assertTrue( properties.getKnownProperties().size() == 0);
	}
	
	/**
	 * Test Case insensitive
	 * @throws Exception
	 */
	@Test
	public void testCaseInsensitiveProperty() throws Exception{
	     
       String uri = "classpath:resources/jdbc.properties";
        
        List<String> propertySourceUris = Arrays.asList(uri);
        
        
         // invoke the property parser and print out properties alphabetically
        AppPropertiesManager manager = new TrialAppPropertiesManager();
        
        AppProperties properties=manager.loadProps(propertySourceUris);

	    assertEquals( properties.get("jpA.showSQL"),true);	
	}
	
    

	/**
	 * Test validations
	 * @throws Exception
	 */
	@Test
    public void testValidation() throws Exception{
         
       String uri = "classpath:resources/jdbc.properties";
        
        List<String> propertySourceUris = Arrays.asList(uri);
        
        
         // invoke the property parser and print out properties alphabetically
        AppPropertiesManager manager = new TrialAppPropertiesManager();
        
        AppProperties properties=manager.loadProps(propertySourceUris);
        assertEquals( properties.isValid(),true);  
    }
    

	/**
	 * Test clear Function
	 * @throws Exception
	 */
    @Test
    public void testClearAll() throws Exception{
         
       String uri = "classpath:resources/jdbc.properties";
        
        List<String> propertySourceUris = Arrays.asList(uri);
        
        
         // invoke the property parser and print out properties alphabetically
        AppPropertiesManager manager = new TrialAppPropertiesManager();
        
        AppProperties properties=manager.loadProps(propertySourceUris);
        
        assertEquals( properties.getKnownProperties().size()==7,true);
        
        properties.clear();
        
        assertEquals( properties.getKnownProperties().size()==0,true);
    }
	
    
    /**
     * Test null file name arguments
     * @throws Exception
     */
    @Test(expected=IllegalArgumentException.class)
    public void testNullFileArguments() throws Exception{
      
      
        
       // invoke the property parser and print out properties alphabetically
      AppPropertiesManager manager = new TrialAppPropertiesManager();
      
      AppProperties properties=manager.loadProps(null);
      
      assertEquals( properties.getKnownProperties().size()==7,true);

    }
    
    /**
     * Test Data Types
     * @throws Exception
     */
    @Test
    public void testDataType() throws Exception{
      
      String uri = "classpath:resources/jdbc.properties";
      
      List<String> propertySourceUris = Arrays.asList(uri);
 
        
       // invoke the property parser and print out properties alphabetically
      AppPropertiesManager manager = new TrialAppPropertiesManager();
      
      AppProperties properties=manager.loadProps(propertySourceUris);
      
      assertTrue( properties.get("hibernate.show_sql") instanceof Boolean);

      assertTrue( properties.get("JDBC_DRIVER") instanceof Driver);

    }
  
 
    
    /**
     * Test Missing Properties
     * @throws Exception
     */
    @Test
    public void tesMissingProperties() throws Exception{
      
      String uri = "classpath:resources/jdbc2.properties";
      
      List<String> propertySourceUris = Arrays.asList(uri);
 
        
       // invoke the property parser and print out properties alphabetically
      AppPropertiesManager manager = new TrialAppPropertiesManager();
      
      AppProperties properties=manager.loadProps(propertySourceUris);
      
      assertEquals( properties.getMissingProperties().size(),1);

    }
 
	private  void registerPropertyTypes(){
		
		PropertyTypeDetector typeDetector=PropertyTypeDetector.getInstance();
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
