package com.AutomationPractice.helper.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class WaitHelper {

	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	private WebDriver driver;

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void setImplicitWait(long timeout, TimeUnit timeunit) {
		driver.manage().timeouts().implicitlyWait(timeout, timeunit);
		log.info("Implicit wait has been set to:" + timeout);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryMilliSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryMilliSeconds));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
			int pollingEveryMilliSeconds) {
		log.info("Waiting for: " + element.toString() + " for " + timeOutInSeconds + " seconds..... ");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryMilliSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is now visible...... ");
	}

	public void waitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for: " + element.toString() + " for " + timeOutInSeconds + " seconds..... ");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is now clickable...... ");
	}

	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		log.info("Waiting for: " + element.toString() + " for " + timeOutInSeconds + " seconds..... ");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Element is now invisible...... ");
		return status;
	}

	public void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		log.info("Waiting for: " + element.toString() + " for " + timeOutInSeconds + " seconds..... ");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame is now available and swithed to...... ");
	}

	private Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMilliSec)).ignoring(NoSuchElementException.class);
		return fluentWait;
	}

	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		Wait<WebDriver> fluentWait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
		fluentWait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public void pageLoadTime(long timeInSeconds, TimeUnit unit) {
		log.info("Waiting for page to load for: " + unit + " seconds");
		driver.manage().timeouts().pageLoadTimeout(timeInSeconds, unit);
		log.info("page is loaded......");
	}

	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for : " + element.toString() + " for : " + timeOutInSeconds + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is now visible ....");
	}

}
