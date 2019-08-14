package com.AutomationPractice.testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.AutomationPractice.helper.browserconfiguratiion.BrowserType;
import com.AutomationPractice.helper.browserconfiguratiion.ChromeBrowser;
import com.AutomationPractice.helper.browserconfiguratiion.FirefoxBrowser;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.browserconfiguratiion.config.PropertyReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestBase {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		setupDriver(ObjectReader.reader.getBrowserType());
	}

	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getSimpleName());
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + " test started.");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " has passed.");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		extent.flush();
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getBrowserObject(BrowserType browserType) throws Exception {
		try {
			switch (browserType) {
			case Chrome:
				// Create an Object of the ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions chromeOptions = chrome.getChromeOptions();
				return chrome.getChromeDriver(chromeOptions);
			case Firefox:
				// Create an Object of the FirefoxBrowser class
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions firefoxOptions = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(firefoxOptions);
			default:
				throw new Exception("Driver not found " + browserType.name());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	public void setupDriver(BrowserType browserType) throws Exception {
		driver = getBrowserObject(browserType);
		log.info("Initialize Web driver " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void logExtentReport(String message) {
		test.log(Status.INFO, message);
	}

	public void getApplicationUrl(String url) {
		logExtentReport("Navigating to URL.... " + url);
		driver.get(url);
	}

}
