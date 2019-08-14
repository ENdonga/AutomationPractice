package com.AutomationPractice.helper.generic;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.testbase.TestBase;

public class ErrorHelper {
	private Logger log = LoggerHelper.getLogger(ErrorHelper.class);
	private WebDriver driver;
	
	public ErrorHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	public String verifyErrorMessage(WebElement element) {
		if (null == element) {
			log.info("WebElement is null...... ");
			return null;
		}
		boolean status = new VerificationHelper(driver).isDisplayed(element);
		if (status) {
			log.info("Error text is..... " + element.getText());
			TestBase.logExtentReport("Error text is..... " + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

}
