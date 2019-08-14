package com.AutomationPractice.testscripts.productcategorypage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.AutomationPractice.helper.assertion.AssertionHelper;
import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.pageobject.NavigationMenu;
import com.AutomationPractice.pageobject.ProductCategoryPage;
import com.AutomationPractice.pageobject.ShoppingCartSummaryPage;
import com.AutomationPractice.testbase.TestBase;

public class ProductCategoryPageTest extends TestBase{
	private final Logger log = LoggerHelper.getLogger(ProductCategoryPageTest.class);
	NavigationMenu navigationMenu;
	ProductCategoryPage productCategoryPage;
	ShoppingCartSummaryPage shoppingCartSummaryPage;
	
	@Test(priority = 1)
	public void verifyProductPageTitleTest() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		navigationMenu = new NavigationMenu(driver);
		productCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		String title = productCategoryPage.getPageTitle();
		String actual = new VerificationHelper(driver).getTitle();
		AssertionHelper.verifyText(actual, title);
		log.info("Test completed....... ");
		TestBase.logExtentReport("Test completed....... ");
	}
	
	@Test(priority = 2)
	public void addItemsToCartTest() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		navigationMenu = new NavigationMenu(driver);
		productCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		productCategoryPage.selectFirstProduct();
		boolean status = productCategoryPage.isCartModalPopupDisplayed();
		AssertionHelper.updateTestStatus(status);
		log.info("Test completed....... ");
		TestBase.logExtentReport("Test completed....... ");
	}

//	@Test
//	public void testAddItemToCart() {
//		getApplicationUrl(ObjectReader.reader.getUrl());
//		navigationMenu = new NavigationMenu(driver);
//		productCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
//		productCategoryPage.clickOnAddToCart();
//		boolean status = productCategoryPage.isCartModalPopupDisplayed();
//		AssertionHelper.updateTestStatus(status);
//		log.info("Test completed....... ");
//		TestBase.logExtentReport("Test completed....... ");
//	}
	
	@Test(priority = 3)
	public void testProductsHaveBeenAddedToCart() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		navigationMenu = new NavigationMenu(driver);
		productCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		productCategoryPage.selectFirstProduct();
//		boolean status = productCategoryPage.isCartModalPopupDisplayed();
//		AssertionHelper.updateTestStatus(status);
		shoppingCartSummaryPage = productCategoryPage.clickOnProceedToCheckout();
		boolean test = shoppingCartSummaryPage.verifyShoppingCartTotalAmount();
		AssertionHelper.updateTestStatus(test);
		log.info("Test completed....... ");
		TestBase.logExtentReport("Test completed....... ");
	}
}
