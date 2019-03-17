package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to configure the project. It is use to
 * remove the hardcoded paths in the java files.
 * @author sandeepchowdaryannabathuni
 *
 */
public class Config {
		
	private final static String CONFIG = "src/config.properties";
	
	
	public static String getProperty(String key) throws IOException {
		String property = null;
		
		FileInputStream file = new FileInputStream(CONFIG);
		Properties prop = new Properties();
		
		prop.load(file);
		
		property = prop.getProperty(key);
		
		return property;
	}
}
