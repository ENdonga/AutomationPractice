package com.AutomationPractice.helper.browserconfiguratiion.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.AutomationPractice.helper.browserconfiguratiion.BrowserType;
import com.AutomationPractice.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader{

	private static FileInputStream file;
	public static Properties properties;
	
	public PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("/src/main/resources/configfile/config.properties");
			file = new FileInputStream(new File(filePath));
			properties = new Properties();
			properties.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getImplicitWait() {
		return Integer.parseInt(properties.getProperty("implicitwait"));
	}

	@Override
	public int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("explicitwait"));
	}

	@Override
	public int getPageLoadTime() {
		return Integer.parseInt(properties.getProperty("pageloadtime"));
	}

	@Override
	public BrowserType getBrowserType() {
		return BrowserType.valueOf(properties.getProperty("browserType"));
	}

	@Override
	public String getUrl() {
		if(System.getProperty("url")!=null) {
			return System.getProperty("url");
		}
		System.out.println(properties.getProperty("applicationUrl"));
		return properties.getProperty("applicationUrl");
	}

	@Override
	public String getUserName() {
		if(System.getProperty("username")!=null) {
			return System.getProperty("username");
		}
		return properties.getProperty("username");
	}

	@Override
	public String getPassword() {
		if(System.getProperty("password")!=null) {
			return System.getProperty("password");
		}
		return properties.getProperty("password");
	}

}
