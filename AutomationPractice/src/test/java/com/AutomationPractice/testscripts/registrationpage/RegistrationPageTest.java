package com.AutomationPractice.testscripts.registrationpage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.AutomationPractice.helper.assertion.AssertionHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.excel.ExcelHelper;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.resource.ResourceHelper;
import com.AutomationPractice.pageobject.LoginPage;
import com.AutomationPractice.pageobject.MyAccountPage;
import com.AutomationPractice.pageobject.NavigationMenu;
import com.AutomationPractice.pageobject.RegistrationPage;
import com.AutomationPractice.testbase.TestBase;

public class RegistrationPageTest extends TestBase {
	private final Logger log = LoggerHelper.getLogger(RegistrationPageTest.class);
	NavigationMenu navigationMenu;
	LoginPage loginPage;
	RegistrationPage registrationPage;
	MyAccountPage myAccountPage;

	/*
	 * @Test(priority = 1) public void testCreateAccount() {
	 * getApplicationUrl(ObjectReader.reader.getUrl()); navigationMenu = new
	 * NavigationMenu(driver); navigationMenu.clickOnSignInLink(); loginPage = new
	 * LoginPage(driver); registrationPage = loginPage.clickOnCreateAnAccount(); //
	 * TestBase.logExtentReport(errorMessage); boolean status =
	 * loginPage.messageToVerify(); AssertionHelper.updateTestStatus(status);
	 * log.info("Test completed.....");
	 * TestBase.logExtentReport("Test completed....."); }
	 * 
	 * @Test(priority = 2) public void testRegisterUser() { // Navigate to URL
	 * getApplicationUrl(ObjectReader.reader.getUrl()); navigationMenu = new
	 * NavigationMenu(driver); // Navigate to sign in page
	 * navigationMenu.clickOnSignInLink(); loginPage = new LoginPage(driver); //
	 * Navigate to registration form registrationPage =
	 * loginPage.clickOnCreateAnAccount();
	 * 
	 * // Enter registration data registrationPage = new RegistrationPage(driver);
	 * registrationPage.setMrRadioButton(); registrationPage.setFirstName("Test");
	 * registrationPage.setLastName("Usertest");
	 * registrationPage.setPassword("password"); registrationPage.setDay("20");
	 * registrationPage.setMonth("July"); registrationPage.setYear("2019");
	 * registrationPage.setSignupNewsletter();
	 * registrationPage.setReceiveSpecialOffersOptIn();
	 * registrationPage.setYourAddressFirstName("YourFirstName");
	 * registrationPage.setYourAddressLastName("YourLastname");
	 * registrationPage.setYourAddressCompany("YourCompany");
	 * registrationPage.setYourAddressAddressLine1("Address1");
	 * registrationPage.setYourAddressAddresLine2("address2");
	 * registrationPage.setYourAddressCity("Nairobi");
	 * registrationPage.setYourAddressState("Florida");
	 * registrationPage.setYourAddressZipCode("10010");
	 * registrationPage.setYourAddressCountry("United States"); registrationPage.
	 * setAdditionalInformation("This is for testing automation purposes");
	 * registrationPage.setHomePhoneNumber("123456789");
	 * registrationPage.setMobileNumber("123456789");
	 * registrationPage.setAliasAddress("Alias Address");
	 * 
	 * // click register button registrationPage.clickOnRegisterButton();
	 * 
	 * myAccountPage = new MyAccountPage(driver); boolean status =
	 * myAccountPage.isMyAccountMessage();
	 * 
	 * AssertionHelper.updateTestStatus(status);
	 * log.info("Test completed successfully....");
	 * TestBase.logExtentReport("Test completed successfully...."); }
	 */
	
	@Test(dataProvider = "getRegistrationData")
	public void registerUsersFromDataFile(String firstname, String lastname, String password, String address1, String city, double zipcode, String country, double phone) {
		// Navigate to URL
		getApplicationUrl(ObjectReader.reader.getUrl());
		navigationMenu = new NavigationMenu(driver);
		// Navigate to sign in page
		navigationMenu.clickOnSignInLink();
		loginPage = new LoginPage(driver);
		// Navigate to registration form
		registrationPage = loginPage.clickOnCreateAnAccount();
		
		// Enter registration data from an excel file
		registrationPage.registerUserWithTestData(firstname, lastname, password, address1, city, zipcode, country, phone);
		
	}
	
	@DataProvider()
	public Object[][] getRegistrationData() {
		Object[][] data = ExcelHelper.getExcelData(ResourceHelper.getResourcePath("/src/main/resources/excel/registrationdata.xlsx"), "Sheet1");
		return data;
	}
}