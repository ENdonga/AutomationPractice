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

public class AddressPage {
	private Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//h1[@class='page-heading']")
	WebElement addressHeadingText;
	@FindBy(xpath = "//select[@id='id_address_delivery']")
	WebElement chooseDeliveryAddressText;
	@FindBy(id = "id_address_delivery")
	WebElement deliveryAddressDropdown;
	@FindBy(xpath = "//p[@class='checkbox addressesAreEquals']")
	WebElement deliveryAddressCheckbox;
	@FindBy(xpath = "//button[@name='processAddress']")
	WebElement proceedToCheckoutBtn;
	
	public AddressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(addressHeadingText, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}
	
	public boolean verifyChooseDeliveryAddressText() {
		return new VerificationHelper(driver).isDisplayed(chooseDeliveryAddressText);
	}
	public ShippingPage clickOnProceedToCheckoutButton() {
		new JavaScriptHelper(driver).scrollToElement(chooseDeliveryAddressText);
		proceedToCheckoutBtn.click();
		log.info("Clicked proceed to checkout button in shopping cart address page...... ");
		TestBase.logExtentReport("Clicked proceed to checkout button in shopping cart address page...... ");
		return new ShippingPage(driver);
	}

}
