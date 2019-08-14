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

public class OrderHistoryPage {

	private Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//h1[@class='page-heading bottom-indent']")
	WebElement orderHistoryHeading;
	@FindBy(xpath = "//p[@class='info-title']")
	WebElement ordersPlacedText;
	@FindBy(id = "order-list")
	WebElement ordersTable;

	public OrderHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(orderHistoryHeading, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public boolean verifyOrderHistoryHeading() {
		return new VerificationHelper(driver).isDisplayed(orderHistoryHeading);
	}
	public boolean verifyOrderPlacedMessage() {
		return new VerificationHelper(driver).isDisplayed(ordersPlacedText);
	}
	public boolean verifyOrdersTable() {
		return new VerificationHelper(driver).isDisplayed(ordersTable);
	}
}
