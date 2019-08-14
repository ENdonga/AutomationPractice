package com.AutomationPractice.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.javascript.JavaScriptHelper;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class PaymentPage {
	private Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//h1[@class='page-heading']")
	WebElement paymentPageHeadingText;
	@FindBy(xpath = "//span[@id='total_price']")
	WebElement amountTotal;
	@FindBy(xpath = "//a[@class='bankwire']")
	WebElement payByBankWire;
	@FindBy(xpath = "//a[@class='cheque']")
	WebElement payByCheck;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(paymentPageHeadingText, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}

	public OrderSummaryPage clickOnPayByBankWire() {
		new JavaScriptHelper(driver).scrollToElement(paymentPageHeadingText);
		payByBankWire.click();
		log.info("Clicked pay by bank wire mode...... ");
		TestBase.logExtentReport("Clicked pay by bank wire mode...... ");
		return new OrderSummaryPage(driver);
	}

	public OrderSummaryPage clickOnPayByCheck() {
		new JavaScriptHelper(driver).scrollToElement(paymentPageHeadingText);
		payByCheck.click();
		log.info("Clicked pay by check mode...... ");
		TestBase.logExtentReport("Clicked pay by check mode...... ");
		return new OrderSummaryPage(driver);
	}

	public boolean verifyPayByWireMode() {
		return new VerificationHelper(driver).isDisplayed(payByBankWire);
	}

	public boolean verifyPayByCheckMode() {
		return new VerificationHelper(driver).isDisplayed(payByCheck);
	}

	public boolean verifyPaymentPageHeadingText() {
		return new VerificationHelper(driver).isDisplayed(paymentPageHeadingText);
	}
}
