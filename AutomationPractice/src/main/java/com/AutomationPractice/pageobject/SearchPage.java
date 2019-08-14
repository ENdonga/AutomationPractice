package com.AutomationPractice.pageobject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class SearchPage {
	private Logger log = LoggerHelper.getLogger(SearchPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	
	@FindBy(xpath = "//input[@id='search_query_top']")
	WebElement searchBox;
	@FindBy(xpath = "//button[@name='submit_search']")
	WebElement searchIcon;
	@FindBy(xpath = "//span[contains(@class,'lighter')]")
	public WebElement searchedText;
	@FindBy(xpath = "//span[contains(@class,'heading-counter')]")
	WebElement resultsMatch;
	@FindBy(xpath = "//*[@id='center_column']/ul/li/div/div[1]")
	List<WebElement> allSearchProducts;
	@FindBy(xpath = "//p[@class='alert alert-warning']")
	WebElement noResultsWereFoundText;
	@FindBy(xpath = "//h1[contains(@class,'product-listing')]")
	public WebElement searchText;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(searchBox, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("Navigation menu object created....");
	}
	
	public String  getSearcResultsPageTitle() {
		return new VerificationHelper(driver).getTitle();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void performSearch(String searchText) {
		String text = searchBox.getAttribute("value");
		if(!text.isEmpty()) {
			clearSearchBox(searchBox);
			searchBox.sendKeys(searchText);
			log.info("Entering search text as.... " + searchText);
			TestBase.logExtentReport("Entering search text as.... " + searchText);
			searchBox.click();
		} else {
			clearSearchBox(searchBox);
			searchBox.sendKeys(searchText);
			log.info("Entering search text as.... " + searchText);
			TestBase.logExtentReport("Entering search text as.... " + searchText);
			searchBox.click();
		}
	}
	
	public void enterTextInSearchAndClickEnter(String text) {
		String s1 = searchBox.getAttribute("value");
		if(!s1.isEmpty()) {
			clearSearchBox(searchBox);
			searchBox.sendKeys(text);
			log.info("Entering search text as.... " + text);
			TestBase.logExtentReport("Entering search text as.... " + text);
			searchBox.sendKeys(Keys.ENTER);
		} else {
			clearSearchBox(searchBox);
			searchBox.sendKeys(text);
			log.info("Entering search text as.... " + text);
			TestBase.logExtentReport("Entering search text as.... " + text);
			searchBox.sendKeys(Keys.ENTER);
		}
	}

	public void clearSearchBox(WebElement element) {
		element.clear();
		log.info("Clearing input field.... ");
		TestBase.logExtentReport("Clearing input field.... ");
	}
	
	public String getWarningMessage() {
		if(resultsMatch.getText().contains("0")) {
			return new VerificationHelper(driver).getElementText(noResultsWereFoundText);
		} else {
			return resultsMatch.getText();
		}
		
	}
	public String getSearchedText() {
		if(!searchText.getText().contains("0")) {
			return searchText.getText();
		}
		return searchText.getText();
	}
}


















