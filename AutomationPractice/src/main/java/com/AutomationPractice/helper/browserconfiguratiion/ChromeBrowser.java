package com.AutomationPractice.helper.browserconfiguratiion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.AutomationPractice.helper.resource.ResourceHelper;

public class ChromeBrowser {
	public ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--disable-popup-blocking");
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		chromeCapabilities.setJavascriptEnabled(true);
		chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeCapabilities);
		
		/*
		 * Future self- Note.
		 * chromeOptions.addArguments --headless parameters to the browser is not being started in the foreground.
		 * Due to this I commented this lines for Linux systems to trigger browser to be started properly.
		 */
		// Linux
//		if(System.getProperty("os.name").contains("Linux")) {
//			chromeOptions.addArguments("--headless", "window-size=1024,768","--no-sandbox");
//		}
		return chromeOptions;
	}
	
	public WebDriver getChromeDriver(ChromeOptions options) {
		if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/linuxdrivers/chromedriver"));
			return new ChromeDriver(options);
		}
		if(System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/windowsdrivers/chromedriver.exe"));
			return new ChromeDriver(options);
		}
		if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/src/main/resources/drivers/macdrivers/chromedriver"));
			return new ChromeDriver(options);
		}
		return null;
		
	}

}
