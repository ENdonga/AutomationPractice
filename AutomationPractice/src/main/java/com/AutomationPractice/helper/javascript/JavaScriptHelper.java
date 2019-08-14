package com.AutomationPractice.helper.javascript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class JavaScriptHelper {
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	private WebDriver driver;

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavascriptHelper has been initialized...... ");
	}

	public Object executeScript(String script) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript(script, args);
	}

	public void scrollToElement(WebElement element) {
		log.info("Scrolling to WebElement....." );
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
	}

	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("Element has been clicked...... ");
	}

	public void scrollIntoView(WebElement element) {
		log.info("Scrolling upto the webelement...... ");
		executeScript("arguments[0].scrollIntoView()", element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("Element has been clicked....... ");
	}

	public void scrollDownVertically() {
		log.info("Scrolling down vertically...... ");
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		log.info("Scrolling up vertically....... ");
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel(int pixel) {
		//executeScript("window.scrollBy(0," + pixel + ")");
		executeScript("window.scrollBy(0, "+ pixel + ")", "");
	}

	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBy(0,-" + pixel + ")");
	}

	public void zoomInBy100Percent() {
		log.info("Zooming page by 100%....... ");
		executeScript("document.body.style.zoom='100%'");
	}

	public void zoomInBy40Percent() {
		log.info("Zooming page by 40%....... ");
		executeScript("document.body.style.zoom='40%'");
	}

	public void clickElement(WebElement element) {
		executeScript("arguments[0].click();", element);
		log.info("Element has been clicked....... ");
	}
}
