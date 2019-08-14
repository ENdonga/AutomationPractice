package com.AutomationPractice.testscripts.shoppingcartpage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.AutomationPractice.helper.assertion.AssertionHelper;
import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.pageobject.AddressPage;
import com.AutomationPractice.pageobject.LoginPage;
import com.AutomationPractice.pageobject.NavigationMenu;
import com.AutomationPractice.pageobject.OrderConfirmationPage;
import com.AutomationPractice.pageobject.OrderHistoryPage;
import com.AutomationPractice.pageobject.OrderSummaryPage;
import com.AutomationPractice.pageobject.PaymentPage;
import com.AutomationPractice.pageobject.ProductCategoryPage;
import com.AutomationPractice.pageobject.ShippingPage;
import com.AutomationPractice.pageobject.ShoppingCartSummaryPage;
import com.AutomationPractice.testbase.TestBase;
import com.AutomationPractice.testscripts.productcategorypage.ProductCategoryPageTest;

public class ShoppingCartSummaryPageTest extends TestBase {
	private final Logger log = LoggerHelper.getLogger(ProductCategoryPageTest.class);
	NavigationMenu navigationMenu;
	LoginPage loginPage;
	ProductCategoryPage productCategoryPage;
	ShoppingCartSummaryPage shoppingCartSummaryPage;
	AddressPage addressPage;
	ShippingPage shippingPage;
	PaymentPage paymentPage;
	OrderSummaryPage orderSummaryPage;
	OrderConfirmationPage orderConfirmationPage;
	OrderHistoryPage orderHistoryPage;

	@Test(priority = 1)
	public void verifyShoppingCartSummaryPageTitle() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		navigationMenu = new NavigationMenu(driver);
		productCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		shoppingCartSummaryPage = navigationMenu.clickOnCartButton();
		String title = shoppingCartSummaryPage.getPageTitle();
		String actual = new VerificationHelper(driver).getTitle();
		AssertionHelper.verifyText(actual, title);
		log.info("Test completed....... ");
		TestBase.logExtentReport("Test completed....... ");
	}

	@Test(priority = 2)
	public void testLoginToShoppingCartSummary() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		navigationMenu = new NavigationMenu(driver);
		//productCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		shoppingCartSummaryPage = navigationMenu.clickOnCartButton();
		String title = shoppingCartSummaryPage.getPageTitle();
		String actual = new VerificationHelper(driver).getTitle();
		AssertionHelper.verifyText(actual, title);
		navigationMenu.logoutOfApplication();
		log.info("Test completed....... ");
		TestBase.logExtentReport("Test completed....... ");
	}

	@Test(priority = 3)
	public void testCheckout() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		navigationMenu = new NavigationMenu(driver);
		productCategoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		productCategoryPage.selectFirstProduct();
		shoppingCartSummaryPage = navigationMenu.clickOnCartButton();
		addressPage = shoppingCartSummaryPage.clickOnProceedToCheckOutButton();
		shippingPage = addressPage.clickOnProceedToCheckoutButton();
		paymentPage = shippingPage.clickOnProceedToCheckouButton();
		orderSummaryPage = paymentPage.clickOnPayByCheck();
		orderConfirmationPage = orderSummaryPage.clickOnConfirmOrderBtn();
		orderHistoryPage = orderConfirmationPage.clickOnBackToOrders();
		boolean test = orderHistoryPage.verifyOrdersTable();
		AssertionHelper.updateTestStatus(test);
		navigationMenu.logoutOfApplication();
		log.info("Test completed....... ");
		TestBase.logExtentReport("Test completed....... ");
	}
}
