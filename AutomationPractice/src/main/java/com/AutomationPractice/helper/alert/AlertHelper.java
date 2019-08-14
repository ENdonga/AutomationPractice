package com.AutomationPractice.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class AlertHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
		log.info("Alert Helper object is created..... ");
	}

	public Alert getAlert() {
		log.info("Alert test..." + driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		getAlert().accept();
		log.info("Alert has been accepted...... ");
	}

	public void dismissAlert() {
		getAlert().dismiss();
		log.info("Alert has been dismissed...... ");
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info("Alert text is...... " + text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("Alert is present...... ");
			return true;
		} catch (NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("Alert is not present..... ");
		}
	}

	public void dismissAlertIfPresent() {
		if (isAlertPresent()) {
			dismissAlert();
		} else {
			log.info("Alert is not present...... ");
		}
	}

	public void acceptPrompts(String text) {
		if (isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("Alert text is: " + text);
		}
	}

}
