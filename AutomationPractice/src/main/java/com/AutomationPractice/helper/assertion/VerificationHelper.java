package com.AutomationPractice.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class VerificationHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);

	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("Element is present...... " + element.getText());
			return true;
		} catch (Exception e) {
			log.error("Element is not present...... ", e.getCause());
			return false;
		}
	}

	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("Element is present...... " + element.getText());
			return false;
		} catch (Exception e) {
			log.error("Element is not present....... ");
			return true;
		}
	}

	public String getElementText(WebElement element) {
		if (null == element) {
			log.info("WebElement is null...... ");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("Element text is..... " + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

	public String getTitle() {
		log.info("Page title is...... " + driver.getTitle());
		return driver.getTitle();
	}

	public String verifyErrorMessage(WebElement element) {
		if (null == element) {
			log.info("WebElement is null...... ");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("Error text is..... " + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}
}
