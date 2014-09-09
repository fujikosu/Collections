package com.kf.samples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * get the connection data from properties
 */
public class GetConnecticonPropertyValues {
	private String[] propDatas;
	//propDatas include driver,url,user and password
	public String[] getPropValues() {
		Properties prop = new Properties();
		InputStream is =null;
		propDatas = new String[4];
		try{
		String propFileName = "config.properties";
		is = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(is);
		if(is == null){
			System.out.println("property file '" + propFileName + "' not found in the classpath");	
		}
		
		propDatas[0] = prop.getProperty("driver");
		propDatas[1] = prop.getProperty("url");
		propDatas[2] = prop.getProperty("user");
		propDatas[3] = prop.getProperty("password");
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			if(is != null){
				try{
					is.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return propDatas;
	}
}
