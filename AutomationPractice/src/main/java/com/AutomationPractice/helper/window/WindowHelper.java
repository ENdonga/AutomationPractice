package com.AutomationPractice.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class WindowHelper {
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);
	private WebDriver driver;

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This methods switches to the parent window
	 */
	public void switchToParentWindow() {
		log.info("Switching to parent window.....");
		driver.switchTo().defaultContent();
	}

	/**
	 * This method will switch to child windows based on index
	 * 
	 * @param windowIndex
	 */
	public void switchToWindow(int windowIndex) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == windowIndex) {
				log.info("Switched to: " + windowIndex + " window");
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	/**
	 * This method will close all tabs windows and switch to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainWindow)) {
				driver.close();
			}
		}
		log.info("Switched to main window");
		driver.switchTo().window(mainWindow);
	}

	public void navigateBack() {
		log.info("Navigating back......");
		driver.navigate().back();
	}

	public void navigateForward() {
		log.info("Navigating forward......");
		driver.navigate().forward();
	}

}
