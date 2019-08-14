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

public class MyAccountPage {

	private final Logger log = LoggerHelper.getLogger(MyAccountPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//p[@class='info-account']")
	public WebElement myAccountPageMessage;
	@FindBy(xpath = "//h1[@class='page-heading']")
	public WebElement myAccount;
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(myAccount, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("MyAccountPage object has been created....");
	}

	public boolean isMyAccountMessage() {
		return new VerificationHelper(driver).isDisplayed(myAccountPageMessage);
	}
}
