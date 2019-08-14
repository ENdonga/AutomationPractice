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

public class OrderConfirmationPage {

	private Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//p[@class='alert alert-success']")
	WebElement 	orderSuccessMsg;
	@FindBy(xpath = "//h3[@class='page-subheading']")
	WebElement heading3Text;
	@FindBy(xpath = "//a[@class='button-exclusive btn btn-default']")
	WebElement backToOrdersButton;
	@FindBy(xpath = "//a[contains(text(),'customer service department.')]")
	WebElement customerServiceDeptLink;

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(orderSuccessMsg, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	public boolean verifyOrderSuccessMessage() {
		return new VerificationHelper(driver).isDisplayed(orderSuccessMsg);
	}
	public OrderHistoryPage clickOnBackToOrders() {
		backToOrdersButton.click();
		log.info("Clicked back to orders link...... ");
		TestBase.logExtentReport("Clicked back to orders link...... ");
		return new OrderHistoryPage(driver);
	}
}
