package com.utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.testng.annotations.Test;

public class ReadConfigInpData {
	
	public static String propFileName;
	public static InputStream fileInput;
	static String linkCount;
	public static String startVal;
	public static String endVal;
	
	
	
  @Test
  public static void readConfigData() throws IOException {
	try {
			Properties properties = new Properties();
			propFileName = System.getProperty("user.dir")+"\\config\\data.properties";
	
			InputStream in = new FileInputStream(propFileName);
			properties.load(in);
			startVal = properties.getProperty("StartVal");
			endVal = properties.getProperty("EndVal");
			
		
	} catch (Exception e) {
		System.out.println("Exception: " + e);
	} 
  }
}
