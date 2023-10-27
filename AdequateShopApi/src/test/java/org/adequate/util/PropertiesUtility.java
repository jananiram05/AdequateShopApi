package org.adequate.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class PropertiesUtility {
	 static Properties prop = new Properties();
	 private FileInputStream stream=null;
		private Properties propertyFile=null;
		
		public  Properties loadFile(String filename){
			propertyFile = new Properties();
			String  PropertyFilePath=null;
			switch(filename) {
			case "applicationDataProperties":
				PropertyFilePath =Constants.APPLICATION_PROPERTIES;
								break;
			
			}
			try {
				stream=new FileInputStream(PropertyFilePath);
				propertyFile.load(stream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return propertyFile;
		}
		public String getPropertyValue(String Key) {//,Properties file
			
			String value = propertyFile.getProperty(Key);
			//System.out.println("Property we got from the file is::" + value);
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return value;
		}

}
