package com.AutomationPractice.pageobject;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.javascript.JavaScriptHelper;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class ProductCategoryPage {
	private Logger log = LoggerHelper.getLogger(ProductCategoryPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(xpath = "//span[@class='cat-name']")
	WebElement categoryName;
	@FindBy(xpath = "//p[contains(text(),'Catalog')]")
	public WebElement catalogTextObj;
	@FindBy(xpath = "//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]")
	public WebElement addToCart;
//	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
//	WebElement addToCartBtn;
	@FindBy(xpath = "//ul[@class='product_list grid row']/li")
	public List<WebElement> totalProducts;
	@FindBy(xpath = "//div[@class='right-block']//span[@class='price product-price']")
	List<WebElement> allPriceElements;
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	public WebElement proceedToCheckoutButton;
	@FindBy(xpath = "//a[@class='addToWishlist wishlistProd_1']")
	WebElement addToWishlist;
	@FindBy(xpath = "//span[contains(text(),'Compositions')]")
	public WebElement compostionsSubHeading;
	@FindBy(xpath = "//span[contains(text(),'Styles')]")
	public WebElement stylesSubHeading;
	@FindBy(xpath = "//li[@class='display-title']")
	WebElement viewGridOrList;
	@FindBy(xpath = "//div[@id='layer_cart']")
	WebElement cartModal;
	@FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']//h2[1]")
	WebElement productAddedToCartMsg;

	public ProductCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(categoryName, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("product category page object created....");
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void mouseOverOnProduct(int number) {
		// Get the xpath for the product card i.e one with the image
		int num = number + 1;
		String firstPart = "//*[@id='center_column']/ul/li[";
		String secondPart = "]/div/div[1]/div";
		String fullXpath = firstPart + num + secondPart;
		WebElement addProductToCart = driver.findElement(By.xpath(fullXpath));
		log.info("Doing mouse over on product number: " + num);
		TestBase.logExtentReport("Doing mouse over on product number: " + num);
		new JavaScriptHelper(driver).scrollToElement(addProductToCart);
		Actions action = new Actions(driver);
		action.moveToElement(addProductToCart).build().perform();
		// After hover on product card locate the xpath for the add to cart button
		String path = "//*[@id='center_column']/ul/li["+ num +"]/div/div[2]/div[2]/a[1]/span";
		WebElement addToCartBtn = driver.findElement(By.xpath(path));
		addToCartBtn.click();
	}

	public void clickOnAddToCart() {
		mouseOverOnProduct(selectRandomProduct());
		log.info("Clicked add to cart button... ");
		TestBase.logExtentReport("Clicked add to cart button... ");
	}

	public ShoppingCartSummaryPage clickOnProceedToCheckout() {
		proceedToCheckoutButton.click();
		log.info("Clicked on proceed to checkout button....");
		TestBase.logExtentReport("Clicked on proceed to checkout button....");
		return new ShoppingCartSummaryPage(driver);
	}

	public void selectFirstProduct() {
		Actions action = new Actions(driver);
		log.info("performing mouse over on first product.....");
		TestBase.logExtentReport("performing mouse over on first product....");
		new JavaScriptHelper(driver).scrollToElement(viewGridOrList);
		action.moveToElement(totalProducts.get(0)).build().perform();
		log.info("Clicking add to basket button....");
		TestBase.logExtentReport("Clicking add to basket button....");
		// mouseOverOnProduct(1);
		addToCart.click();
		log.info("Add to basket button clicked....");
		TestBase.logExtentReport("Add to basket button clicked....");
	}

	public int getTotalProducts() {
		log.info("Getting all products in product catalog...");
		TestBase.logExtentReport("Getting all products in product catalog...");
		return totalProducts.size();
	}

	public List<WebElement> getAllProductsPrice() {
		log.info("Getting the prices for all products...");
		TestBase.logExtentReport("Getting the prices for all products...");
		return allPriceElements;
	}

	public boolean isCartModalPopupDisplayed() {
		return new VerificationHelper(driver).isDisplayed(cartModal);
	}

	public int selectRandomProduct() {
		List<WebElement> allProducts = totalProducts;
		Random random = new Random();
		int randomProduct = random.nextInt(allProducts.size());
		return randomProduct;
	}

	public boolean verifyProceedToCheckOutButtonIsDisplayed() {
		return new VerificationHelper(driver).isDisplayed(proceedToCheckoutButton);
	}
}
