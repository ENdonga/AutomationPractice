package com.AutomationPractice.pageobject;

import java.util.Iterator;
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
import com.AutomationPractice.helper.select.DropDownHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class RegistrationPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(RegistrationPage.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//h1[@class='page-heading']")
	public WebElement createAnAccountPageHeading;
	@FindBy(id = "account-creation_form")
	public WebElement createAccountForm;
	@FindBy(xpath = "//h3[contains(text(),'Your personal information')]")
	public WebElement yourPersonalInformation;
	@FindBy(xpath = "//label[contains(text(),'Title')]")
	public WebElement titleText;
	@FindBy(id = "id_gender1")
	public WebElement mrRadioButton;
	@FindBy(id = "id_gender2")
	public WebElement mrsRadioButton;
	@FindBy(id = "customer_firstname")
	public WebElement customerFirstName;
	@FindBy(id = "customer_lastname")
	public WebElement customerLastName;
	@FindBy(id = "email")
	public WebElement customerEmail;
	@FindBy(id = "passwd")
	public WebElement customerPassword;
	@FindBy(id = "days")
	public WebElement dateOfBirthDays;
	@FindBy(id = "months")
	public WebElement dateOfBirthMonth;
	@FindBy(id = "years")
	public WebElement dateOfBirthYear;
	@FindBy(id = "uniform-newsletter")
	public WebElement signupNewsletter;
	@FindBy(xpath = "//label[contains(text(),'Sign up for our newsletter!')]")
	public WebElement signupNewsletterText;
	@FindBy(xpath = "//label[contains(text(),'Receive special offers from our partners!')]")
	public WebElement receiveSpecialOffersText;
	@FindBy(id = "uniform-optin")
	public WebElement specialOffersOptIn;
	@FindBy(xpath = "//h3[contains(text(),'Your address')]")
	public WebElement yourAddressSubHeading;
	@FindBy(id = "firstname")
	public WebElement yourAddressFirstName;
	@FindBy(id = "lastname")
	public WebElement yourAddressLastName;
	@FindBy(id = "company")
	public WebElement yourAddressCompany;
	@FindBy(id = "address1")
	public WebElement address;
	@FindBy(id = "address2")
	public WebElement addressLineTwo;
	@FindBy(id = "city")
	public WebElement yourAddressCity;
	@FindBy(id = "id_state")
	public WebElement yourAddressState;
	@FindBy(id = "postcode")
	public WebElement yourAddressPostalCode;
	@FindBy(id = "id_country")
	public WebElement yourAddressCountry;
	@FindBy(id = "other")
	public WebElement additionalInformation;
	@FindBy(id = "phone")
	public WebElement homePhoneNumber;
	@FindBy(id = "phone_mobile")
	public WebElement mobilePhoneNumber;
	@FindBy(xpath = "//p[@class='inline-infos']")
	public WebElement homePhoneNumberMessage;
	@FindBy(id = "alias")
	public WebElement aliasAddress;
	@FindBy(xpath = "//span[contains(text(),'Register')]")
	public WebElement registerButton;
	@FindBy(xpath = "//span[contains(text(),'Required field')]")
	public WebElement requiredFieldMessage;
	@FindBy(xpath = "//span[contains(text(),'Street address, P.O. Box, Company name, etc.')]")
	public WebElement addressLineOneMessage;
	@FindBy(xpath = "//div[@class='alert alert-danger']")
	WebElement error;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(createAccountForm, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("RegistrationPage object created.....");
	}

	public void setMrRadioButton() {
		mrRadioButton.click();
		log.info("Selected Mr radio button.....");
		TestBase.logExtentReport("Selected Mr radio button.....");
	}

	public void setMrsRadioButton() {
		mrsRadioButton.click();
		log.info("Selected Mrs radio button.....");
		TestBase.logExtentReport("Selected Mrs radio button.....");
	}

	public void setFirstName(String firstName) {
		clearTextFieldBeforeTyping(customerFirstName);
		customerFirstName.sendKeys(firstName);
		log.info("Entered customer first name.... " + firstName);
		TestBase.logExtentReport("Entered customer first name.... " + firstName);
	}

	public void setLastName(String lastName) {
		clearTextFieldBeforeTyping(customerLastName);
		customerLastName.sendKeys(lastName);
		log.info("Entered customer last name.... " + lastName);
		TestBase.logExtentReport("Entered customer last name.... " + lastName);
	}

	public void setEmailAddress(String emailAddress) {
		if (!customerEmail.getText().isEmpty()) {
			log.info("Entering customer email address.... " + customerEmail.getText());
			TestBase.logExtentReport("Entering customer email address.... " + customerEmail.getText());
		} else {
			log.info("Customer email is blank....");
			TestBase.logExtentReport("Customer email is blank....");
			new LoginPage(driver).enterRegistrationEmail();
		}
	}

	public void setPassword(String password) {
		clearTextFieldBeforeTyping(customerPassword);
		customerPassword.sendKeys(password);
		log.info("Entered customer password..... " + password);
		TestBase.logExtentReport("Entered customer password.... " + password);
	}

	public void setDay(String day) {
		List<WebElement> days = driver.findElements(By.xpath("//select[@id='days']//option"));
		Iterator<WebElement> iterator = days.iterator();
		while (iterator.hasNext()) {
			WebElement element = (WebElement) iterator.next();
			String text = element.getText().trim().toString();
			if (text.equalsIgnoreCase(day)) {
				System.out.println(day);
				element.click();
				log.info("Selected day as: " + text);
				TestBase.logExtentReport("Selected day as: " + text);
				break;
			}
		}
	}

	public void setMonth(String month) {
		List<WebElement> months = driver.findElements(By.xpath("//select[@id='months']/option"));
		Iterator<WebElement> iterator = months.iterator();
		while (iterator.hasNext()) {
			WebElement element = (WebElement) iterator.next();
			String text = element.getText().trim().toString();
			if (text.equalsIgnoreCase(month)) {
				System.out.println(month);
				element.click();
				log.info("Selected month as: " + text);
				TestBase.logExtentReport("Selected month as: " + text);
				break;
			}

		}
	}

	public void setYear(String year) {
		List<WebElement> years = driver.findElements(By.xpath("//select[@id='years']/option"));
		Iterator<WebElement> iterator = years.iterator();
		while (iterator.hasNext()) {
			WebElement element = (WebElement) iterator.next();
			String text = element.getText().trim().toString();
			if (text.equalsIgnoreCase(year)) {
				System.out.println(year);
				element.click();
				log.info("Selected year as:" + text);
				TestBase.logExtentReport("Selected year as:" + text);
				break;
			}
		}
	}

	public void setSignupNewsletter() {
		boolean selected = signupNewsletter.isSelected();
		if (!selected) {
			signupNewsletter.click();
			log.info("Selected " + signupNewsletterText.getText() + " checkbox");
			TestBase.logExtentReport("Selected " + signupNewsletterText.getText() + " checkbox");
		}
	}

	public void setReceiveSpecialOffersOptIn() {
		boolean selected = specialOffersOptIn.isSelected();
		if (!selected) {
			specialOffersOptIn.click();
			log.info("Selected " + receiveSpecialOffersText.getText() + " checkbox");
			TestBase.logExtentReport("Selected " + signupNewsletterText.getText() + " checkbox");
		}
	}

	public boolean verifyYourAddressText() {
		return new VerificationHelper(driver).isDisplayed(yourAddressSubHeading);
	}

	public void setYourAddressFirstName(String firstName) {
		clearTextFieldBeforeTyping(yourAddressFirstName);
		yourAddressFirstName.sendKeys(firstName);
		log.info("Entered your address first name: " + firstName);
		TestBase.logExtentReport("Entered your address first name: " + firstName);
	}

	public void setYourAddressLastName(String lastName) {
		clearTextFieldBeforeTyping(yourAddressLastName);
		yourAddressLastName.sendKeys(lastName);
		log.info("Entered your address last name: " + lastName);
		TestBase.logExtentReport("Entered your address last name: " + lastName);
	}

	public void setYourAddressCompany(String company) {
		clearTextFieldBeforeTyping(yourAddressCompany);
		yourAddressCompany.sendKeys(company);
		log.info("Entered your address company name: " + company);
		TestBase.logExtentReport("Entered your address company name: " + company);
	}

	public void setYourAddressAddressLine1(String address1) {
		clearTextFieldBeforeTyping(address);
		address.sendKeys(address1);
		log.info("Entered address line 1 as: " + address1);
		TestBase.logExtentReport("Entered address line 1 as: " + address1);
	}

	public void setYourAddressAddresLine2(String address2) {
		clearTextFieldBeforeTyping(addressLineTwo);
		addressLineTwo.sendKeys(address2);
		log.info("Entered address line 2 as: " + address2);
		TestBase.logExtentReport("Entered address line 2 as: " + address2);
	}

	public void setYourAddressCity(String city) {
		clearTextFieldBeforeTyping(yourAddressCity);
		yourAddressCity.sendKeys(city);
		log.info("Entered city name as: " + city);
		TestBase.logExtentReport("Entered city name as: " + city);
	}

	public void setYourAddressState(String state) {
		new DropDownHelper(driver).selectUsingVisibleText(yourAddressState, state);
		String selectedState = new DropDownHelper(driver).getSelectedOption(yourAddressState);
		log.info("Selected state as.... " + selectedState);
		TestBase.logExtentReport("Selected state as.... " + selectedState);
	}

	public void setYourAddressZipCode(String zipCode) {
		clearTextFieldBeforeTyping(yourAddressPostalCode);
		yourAddressPostalCode.sendKeys(zipCode);
		log.info("Entered zip/postal code: " + zipCode);
		TestBase.logExtentReport("Entered zip/postal code: " + zipCode);
	}

	public void setYourAddressCountry(String countryName) {
		new DropDownHelper(driver).selectUsingVisibleText(yourAddressCountry, countryName);
		String selectedCountry = new DropDownHelper(driver).getSelectedOption(yourAddressCountry);
		log.info("Selected country option as.... " + selectedCountry);
		TestBase.logExtentReport("Selected country option as.... " + selectedCountry);
	}

	public void setAdditionalInformation(String additionalInfo) {
		clearTextFieldBeforeTyping(additionalInformation);
		additionalInformation.sendKeys(additionalInfo);
		log.info("Entered additional information: " + additionalInfo);
		TestBase.logExtentReport("Entered additional information: " + additionalInfo);
	}

	public void setHomePhoneNumber(String homePhone) {
		clearTextFieldBeforeTyping(homePhoneNumber);
		homePhoneNumber.sendKeys(homePhone);
		log.info("Entered home phone number: " + homePhone);
		TestBase.logExtentReport("Entered home phone number: " + homePhone);
	}

	public void setMobileNumber(String mobilePhone) {
		clearTextFieldBeforeTyping(mobilePhoneNumber);
		mobilePhoneNumber.sendKeys(mobilePhone);
		log.info("Entered mobile phone number: " + mobilePhone);
		TestBase.logExtentReport("Entered mobile phone number: " + mobilePhone);
	}

	public void setAliasAddress(String alias) {
		clearTextFieldBeforeTyping(aliasAddress);
		aliasAddress.sendKeys(alias);
		log.info("Entered alias address: " + alias);
		TestBase.logExtentReport("Entered alias address: " + alias);
	}
	
	public void clearTextFieldBeforeTyping(WebElement element) {
		element.clear();
		log.info("Clearing input field.... ");
		TestBase.logExtentReport("Clearing input field.... ");
	}

	public boolean verifyRequiredFieldText() {
		return new VerificationHelper(driver).isDisplayed(requiredFieldMessage);
	}

	public boolean verifyPhoneNumberMessage() {
		return new VerificationHelper(driver).isDisplayed(homePhoneNumberMessage);
	}

	public boolean verifyAddressLineMessage() {
		return new VerificationHelper(driver).isDisplayed(addressLineOneMessage);
	}

	public void clickOnRegisterButton() {
		log.info("Clicking on: " + registerButton.getText() + " button");
		TestBase.logExtentReport("Clicking on: " + registerButton.getText() + " button");
		registerButton.click();
		//return new MyAccountPage(driver);
	}
	
	public boolean verifyRegisterPageSuccess() {
		return new VerificationHelper(driver).isDisplayed(yourPersonalInformation);
	}
	
	public void registerUserWithTestData(String firstname, String lastname, String password, String address1, String city, String zipcode, String country, String phone) {
		clearTextFieldBeforeTyping(customerFirstName);
		log.info("Entering email address ..... " + firstname);
		TestBase.logExtentReport("Entering email address..... " + firstname);
		customerFirstName.sendKeys(firstname);
		clearTextFieldBeforeTyping(customerLastName);
		log.info("Entering email address ..... " + lastname);
		TestBase.logExtentReport("Entering email address..... " + lastname);
		customerLastName.sendKeys(lastname);
		clearTextFieldBeforeTyping(customerPassword);
		log.info("Entering email address ..... " + password);
		TestBase.logExtentReport("Entering email address..... " + password);
		customerPassword.sendKeys(password);
		clearTextFieldBeforeTyping(address);
		address.sendKeys(address1);
		log.info("Entered address line 1 as: " + address1);
		TestBase.logExtentReport("Entered address line 1 as: " + address1);
		clearTextFieldBeforeTyping(yourAddressCity);
		yourAddressCity.sendKeys(city);
		log.info("Entered city name as: " + city);
		TestBase.logExtentReport("Entered city name as: " + city);
		clearTextFieldBeforeTyping(yourAddressPostalCode);
		yourAddressPostalCode.sendKeys(zipcode);
		log.info("Entered zip/postal code: " + zipcode);
		TestBase.logExtentReport("Entered zip/postal code: " + zipcode);
		new DropDownHelper(driver).selectUsingVisibleText(yourAddressCountry, country);
		String selectedCountry = new DropDownHelper(driver).getSelectedOption(yourAddressCountry);
		log.info("Selected country option as.... " + selectedCountry);
		TestBase.logExtentReport("Selected country option as.... " + selectedCountry);
		clearTextFieldBeforeTyping(mobilePhoneNumber);
		mobilePhoneNumber.sendKeys(phone);
		log.info("Entered mobile phone number: " + phone);
		TestBase.logExtentReport("Entered mobile phone number: " + phone);
		//registerButton.click();
	}
}



























