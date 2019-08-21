package com.AutomationPractice.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class NavigationMenu {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(NavigationMenu.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//a[@class='sf-with-ul'][contains(text(),'Women')]")
	public WebElement womenMenu;
	@FindBy(linkText = "Dresses")
	public WebElement dressesMenu;
	@FindBy(linkText = "T-shirts")
	public WebElement tshirtsMenu;
	@FindBy(xpath = "//a[@class='login']")
	public WebElement signInLink;
	@FindBy(xpath = "//b[contains(text(),'Cart')]")
	public WebElement cartButton;
	@FindBy(xpath = "//a[@class='logout']")
	public WebElement signOut;

	public NavigationMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(womenMenu, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("Navigation menu object created....");
	}

	public void mouseOver(String menu) {
		log.info("Doing a mouse over on " + menu);
		TestBase.logExtentReport("Doing a mouse over on " + menu);
		Actions action = new Actions(driver);
		// //*[text()='Women']
		action.moveToElement(driver.findElement(By.linkText(menu))).build().perform();
	}

	public ProductCategoryPage clickOnItem(String submenu) {
		log.info("Clicking on " + submenu);
		TestBase.logExtentReport("Clicking on " + submenu);
		driver.findElement(By.xpath("//a[contains(text(),'" + submenu + "')]")).click();
		return new ProductCategoryPage(driver);
	}

	public ProductCategoryPage clickOnMenu(WebElement element) {
		log.info("Clicking on " + element.getText());
		TestBase.logExtentReport("Clicking on " + element.getText());
		element.click();
		return new ProductCategoryPage(driver);
	}

	public void clickOnSignInLink() {
		signInLink.click();
		log.info("Clicked on sign in link.....");
		TestBase.logExtentReport("Clicked on sign in link.....");
	}

	public ShoppingCartSummaryPage clickOnCartButton() {
		cartButton.click();
		log.info("Clicked on cart button link.....");
		TestBase.logExtentReport("Clicked on cart button link.....");
		return new ShoppingCartSummaryPage(driver);
	}
	
	public void logoutOfApplication() {
		signOut.click();
		log.info("Clicked on sign out.....");
		TestBase.logExtentReport("Clicked on sign out.....");
		waitHelper.waitForElement(signInLink, ObjectReader.reader.getExplicitWait());
	}
}
