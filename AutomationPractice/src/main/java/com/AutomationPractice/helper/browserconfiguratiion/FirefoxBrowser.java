package com.AutomationPractice.helper.browserconfiguratiion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.AutomationPractice.helper.resource.ResourceHelper;

public class FirefoxBrowser {
	public FirefoxOptions getFirefoxOptions() {
		DesiredCapabilities firefox = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);

		FirefoxOptions firefoxOptions = new FirefoxOptions(firefox);
		
		/*
		 * Future self- Note.
		 * FirefoxOptions.addArguments --headless parameters to the browser is not being started in the foreground.
		 * Due to this I commented this lines for Linux systems to trigger browser to be started properly.
		 */
		
		// Linux
//		if (System.getProperty("os.name").contains("Linux")) {
//			firefoxOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
//		}
		return firefoxOptions;
	}

	public WebDriver getFirefoxDriver(FirefoxOptions options) {
		if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.gecko.driver",
					ResourceHelper.getResourcePath("/src/main/resources/drivers/linuxdrivers/geckodriver"));
			return new FirefoxDriver(options);
		}
		if (System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.gecko.driver",
					ResourceHelper.getResourcePath("/src/main/resources/drivers/windowsdrivers/geckodriver.exe"));
			return new FirefoxDriver(options);
		}
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.gecko.driver",
					ResourceHelper.getResourcePath("/src/main/resources/drivers/macdrivers/geckodriver"));
			return new FirefoxDriver(options);
		}
		return null;

	}

}
