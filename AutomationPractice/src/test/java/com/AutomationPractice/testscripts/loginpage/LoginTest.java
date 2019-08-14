package com.AutomationPractice.testscripts.loginpage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.AutomationPractice.helper.assertion.AssertionHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.excel.ExcelHelper;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.resource.ResourceHelper;
import com.AutomationPractice.pageobject.LoginPage;
import com.AutomationPractice.pageobject.NavigationMenu;
import com.AutomationPractice.testbase.TestBase;

public class LoginTest extends TestBase {

	private final Logger log = LoggerHelper.getLogger(LoginTest.class);
	NavigationMenu navigationMenu;

	@Test(priority = 3, description = "Login Test with valid credentials")
	public void testLoginToApplication() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		// Create object of login page
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		boolean status = loginPage.verifySuccessLoginMessage();
		AssertionHelper.updateTestStatus(status);
		navigationMenu.logoutOfApplication();
		log.info("Test completed.....");
		TestBase.logExtentReport("Test completed.....");
	}

	@Test(dataProvider = "getLoginData", priority = 1, description = "Login test with invalid credentials")
	public void testLoginWithInvalidCredentials(String username, String password) {
		getApplicationUrl(ObjectReader.reader.getUrl());
		navigationMenu = new NavigationMenu(driver);
		navigationMenu.clickOnSignInLink();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplicationWithTestData(username, password);
		boolean status = loginPage.messageToVerify();
		AssertionHelper.updateTestStatus(status);
		log.info("Test completed.....");
		TestBase.logExtentReport("Test completed.....");
	}

	@Test(dataProvider = "getLoginData", priority = 2)
	public void testLoginWithDataSet(String username, String password) {
		getApplicationUrl(ObjectReader.reader.getUrl());
		navigationMenu = new NavigationMenu(driver);
		navigationMenu.clickOnSignInLink();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplicationWithTestData(username, password);
		boolean status = loginPage.messageToVerify();
		AssertionHelper.updateTestStatus(status);
		log.info("Test completed.....");
		TestBase.logExtentReport("Test completed.....");
	}

	@DataProvider()
	public Object[][] getLoginData() {
		Object[][] data = ExcelHelper.getExcelData(ResourceHelper.getResourcePath("/src/main/resources/excel/logindata.xlsx"), "Sheet1");
		return data;
	}
}
