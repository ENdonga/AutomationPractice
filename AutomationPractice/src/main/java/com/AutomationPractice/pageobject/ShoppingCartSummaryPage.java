package com.AutomationPractice.pageobject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class ShoppingCartSummaryPage {
	private Logger log = LoggerHelper.getLogger(ShoppingCartSummaryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//h1[@id='cart_title']")
	WebElement shoppingCartSummaryHeading;
	@FindBy(xpath = "//h1[@id='cart_title']/span")
	WebElement yourShoppingContainsMsg;
	@FindBy(xpath = "//i[@class='icon-trash']")
	List<WebElement> quantityDelete;
	@FindBy(xpath = "//p[@class='alert alert-warning']")
	public WebElement emptyShoppingCartMsg;
	@FindBy(xpath = "//td[contains(text(),'Total products')]")
	public WebElement totalProductsText;
	@FindBy(xpath = "//td[@id='total_product']")
	public WebElement totalProductsPrice;
	@FindBy(xpath = "//td[contains(text(),'Total shipping')]")
	public WebElement totalShippingMsg;
	@FindBy(xpath = "//td[@id='total_shipping']")
	public WebElement totalShippingPrice;
	@FindBy(xpath = "//*[@id='cart_summary']/tfoot/tr[5]/td[1]")
	public WebElement subTotalText;
	@FindBy(xpath = "//*[@id='cart_summary']/tfoot/tr[5]/td[2]")
	public WebElement subTotalPrice;
	@FindBy(xpath = "//td[contains(text(),'Tax')]")
	public WebElement taxText;
	@FindBy(xpath = "//td[@id='total_tax']")
	public WebElement totalTax;
	@FindBy(xpath = "//td[@class='total_price_container text-right']")
	public WebElement totalText;
	@FindBy(xpath = "//td[@id='total_price_container']")
	public WebElement totalPrice;
	@FindBy(xpath = "//a[@class='button-exclusive btn btn-default']")
	public WebElement continueShoppingBtn;
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
	public WebElement proceedToCheckoutBtn;
	@FindBy(xpath = "//table[@id='cart_summary']//tbody/tr")
	WebElement shoppingCartProductRows;
	
	
	public ShoppingCartSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(shoppingCartSummaryHeading, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyProduct(String productName) {
		log.info("Selecting product in shopping cart....." + productName);
		TestBase.logExtentReport("Selecting product in shopping cart....." + productName);
		WebElement product = driver.findElement(By.xpath("//tr//p[@class='" + productName + "']"));
		return new VerificationHelper(driver).isDisplayed(product);
	}

	public boolean verifyShoppingCartTotalAmount() {
		return new VerificationHelper(driver).isDisplayed(totalPrice);
	}
	
	public AddressPage clickOnProceedToCheckOutButton() {
		proceedToCheckoutBtn.click();
		log.info("Clicked proceed to checkout button in shopping cart summary page...... ");
		TestBase.logExtentReport("Clicked proceed to checkout button in shopping cart summary page...... ");
		return new AddressPage(driver);
	}
}













