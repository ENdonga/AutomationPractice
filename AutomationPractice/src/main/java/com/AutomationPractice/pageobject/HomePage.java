package com.AutomationPractice.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class HomePage {
	private Logger log = LoggerHelper.getLogger(HomePage.class);
	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//a[@class='homefeatured']")
	public WebElement popularButton;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(popularButton, ObjectReader.reader.getExplicitWait());
		log.info("HomePage object created....");
		TestBase.logExtentReport("HomePage object created....");
	}
	
	public boolean verifyPopularButton() {
		return new VerificationHelper(driver).isDisplayed(popularButton);
	}
}
