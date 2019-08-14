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

public class OrderSummaryPage {
	private Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//h1[@class='page-heading']")
	WebElement orderSummaryHeadingText;
	@FindBy(xpath = "//h3[@class='page-subheading']")
	WebElement paymentModeHeadingText;
	@FindBy(xpath = "//a[@class='button-exclusive btn btn-default']")
	WebElement otherPaymentMethods;
	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	WebElement confirmOrderBtn;
	
	
	public OrderSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(orderSummaryHeadingText, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	public boolean verifyOrderSummaryHeading() {
		return new VerificationHelper(driver).isDisplayed(orderSummaryHeadingText);
	}
	public boolean verifyModeOfPayment() {
		return new VerificationHelper(driver).isDisplayed(paymentModeHeadingText);
	}
	public OrderConfirmationPage clickOnConfirmOrderBtn() {
		confirmOrderBtn.click();
		log.info("Clicked I confirm my order button...... ");
		TestBase.logExtentReport("Clicked I confirm my order button...... ");
		return new OrderConfirmationPage(driver);
	}
}
