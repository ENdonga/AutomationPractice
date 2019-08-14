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

public class ShippingPage {
	private Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//h1[@class='page-heading']")
	WebElement shippingHeading;
	@FindBy(xpath = "//p[contains(text(),'Choose a shipping option for this address: My addr')]")
	WebElement chooseShippingOptionText;
	@FindBy(xpath = "//div[@id='uniform-cgv']")
	WebElement termsOfSeviceCheckBox;
	@FindBy(xpath = "//label[contains(text(),'I agree to the terms of service and will adhere to')]")
	WebElement termsOfSeviceText;
	@FindBy(xpath = "//button[@name='processCarrier']")
	WebElement proceedToCheckoutBtn;

	public ShippingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(shippingHeading, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}

	public boolean verifyChooseShippingOption() {
		return new VerificationHelper(driver).isDisplayed(chooseShippingOptionText);
	}

	public void clickOnTermsOfService() {
		boolean selected = termsOfSeviceCheckBox.isSelected();
		if (!selected) {
			termsOfSeviceCheckBox.click();
			log.info("Selected terms of service checkbox...... ");
			TestBase.logExtentReport("Selected terms of service checkbox...... ");
		} else {
			log.info("Checkbox already selected...... ");
			TestBase.logExtentReport("Checkbox already selected...... ");
		}
	}
	
	public PaymentPage clickOnProceedToCheckouButton() {
		clickOnTermsOfService();
		new JavaScriptHelper(driver).scrollToElement(shippingHeading);
		proceedToCheckoutBtn.click();
		log.info("Clicked proceed to checkout button in shopping cart address page...... ");
		TestBase.logExtentReport("Clicked proceed to checkout button in shopping cart address page...... ");
		return new PaymentPage(driver);
	}
}
