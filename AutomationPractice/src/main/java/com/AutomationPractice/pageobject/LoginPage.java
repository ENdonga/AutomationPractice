package com.AutomationPractice.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.generic.ErrorHelper;
import com.AutomationPractice.helper.javascript.JavaScriptHelper;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class LoginPage {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(LoginPage.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//a[@class='login']")
	WebElement signin;
	@FindBy(xpath = "//h3[contains(text(),'Create an account')]")
	WebElement createAccountHeading;
	@FindBy(id = "email_create")
	WebElement registrationEmailAddress;
	@FindBy(id = "SubmitCreate")
	WebElement createAnAccount;
	@FindBy(xpath = "//h3[contains(text(),'Already registered?')]")
	WebElement signInText;
	@FindBy(id = "email")
	WebElement signInEmailAddress;
	@FindBy(id = "passwd")
	WebElement signInPassword;
	@FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
	WebElement forgotPassword;
	@FindBy(id = "SubmitLogin")
	WebElement submitLogin;
	@FindBy(xpath = "//h1[@class='page-heading']")
	WebElement authentication;
	@FindBy(xpath = "//p[contains(text(),'Please enter your email address to create an accou')]")
	WebElement createAnAccountMessage;
	@FindBy(xpath = "//h1[@class='page-heading']")
	WebElement myAccountHeading;
	@FindBy(xpath = "//div[@class='alert alert-danger']")
	WebElement allErrors;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(signin, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("LoginPage Object created.....");
	}

	public boolean verifyAuthenticationMessage() {
		return new VerificationHelper(driver).isDisplayed(authentication);
	}

	public boolean verifySuccessLoginMessage() {
		return new VerificationHelper(driver).isDisplayed(myAccountHeading);
	}

	public boolean verifyValidationError() {
		return new VerificationHelper(driver).isDisplayed(allErrors);
	}

	public void clickOnSignInLink() {
		signin.click();
		log.info("Clicked on sign in link.....");
		TestBase.logExtentReport("Clicked on sign in link.....");
	}

	public void clearTextFieldBeforeTyping(WebElement element) {
		element.clear();
		log.info("Clearing input field.... ");
		TestBase.logExtentReport("Clearing input field.... ");
	}

	public void enterSignInEmailAddress(String emailAddress) {
		clearTextFieldBeforeTyping(signInEmailAddress);
		log.info("Entering email address ..... " + emailAddress);
		TestBase.logExtentReport("Entering email address..... " + emailAddress);
		signInEmailAddress.sendKeys(emailAddress);
		String username = signInEmailAddress.getAttribute("value");
		if (username.isEmpty()) {
			log.info("Email address field is blank......");
			TestBase.logExtentReport("Email address field is blank......");
		}
	}

	public void enterSignInPassword(String password) {
		clearTextFieldBeforeTyping(signInPassword);
		log.info("Entering password ....");
		TestBase.logExtentReport("Entering password ...." + password);
		signInPassword.sendKeys(password);
		String pass = signInPassword.getAttribute("value");
		if (pass.isEmpty()) {
			log.info("Password field is blank......");
			TestBase.logExtentReport("Password field is blank......");
		}
	}

	public MyAccountPage clickOnSignInButton() {
		log.info("Clicking on sign in button ....");
		TestBase.logExtentReport("Clicking on sign in button....");
		new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		return new MyAccountPage(driver);
	}

	public String enterRegistrationEmail() {
		boolean status = allErrors.isDisplayed();
		if (status) {
			ErrorHelper errorHelper = new ErrorHelper(driver);
			errorHelper.verifyErrorMessage(allErrors);
		} else {
			clearTextFieldBeforeTyping(registrationEmailAddress);
			String email = System.currentTimeMillis() + "@gmail.com";
			log.info("Entering registration email....." + email);
			TestBase.logExtentReport("Entering registration email....." + email);
			registrationEmailAddress.sendKeys(email);
			return email;
		}
		if(!status) {
			messageToVerify();
		}
		return null;
	}

	public RegistrationPage clickOnCreateAnAccount() {
		enterRegistrationEmail();
		createAnAccount.click();
		return new RegistrationPage(driver);
	}

	public void loginToApplication(String emailAddress, String password) {
		clickOnSignInLink();
		boolean status = allErrors.isDisplayed();
		if (status) {
			ErrorHelper errorHelper = new ErrorHelper(driver);
			errorHelper.verifyErrorMessage(allErrors);
		} else {
			new JavaScriptHelper(driver).scrollToElement(signInText);
			enterSignInEmailAddress(emailAddress);
			enterSignInPassword(password);
			clickOnSignInButton();
		}
		if (!status) {
			messageToVerify();
		}
	}

	public void loginToApplicationWithTestData(String emailAddress, String password) {
		boolean status = allErrors.isDisplayed();
		if (status) {
			ErrorHelper errorHelper = new ErrorHelper(driver);
			errorHelper.verifyErrorMessage(allErrors);
		} else {
			clearTextFieldBeforeTyping(signInEmailAddress);
			log.info("Entering email address ..... " + emailAddress);
			TestBase.logExtentReport("Entering email address..... " + emailAddress);
			signInEmailAddress.sendKeys(emailAddress);
			clearTextFieldBeforeTyping(signInPassword);
			log.info("Entering password ....");
			TestBase.logExtentReport("Entering password ...." + password);
			signInPassword.sendKeys(password);
			submitLogin.click();
		}
		if (!status) {
			messageToVerify();
		}

	}

	public boolean messageToVerify() {
		String currentUrl = driver.getCurrentUrl();
		String authenticationUrl = "http://automationpractice.com/index.php?controller=authentication";
		if (currentUrl.equalsIgnoreCase(authenticationUrl)) {
			return verifyValidationError();
		} else {
			return verifySuccessLoginMessage();
		}
	}
}
