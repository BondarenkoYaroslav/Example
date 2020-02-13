package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReaderProperties {
	
	public static String pathFile;
	public static String key;
	
	public static String readFile(String pathFile, String key) {
		
		Properties properties = null;
		String keyValue = null;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(pathFile);
		
		properties = new Properties();
		
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		keyValue = properties.getProperty(key);
		
		if (keyValue == null) {
			System.err.println("Not found key: " + key);
		}
		
		return keyValue;
	}

}
