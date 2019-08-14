package com.AutomationPractice.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.helper.wait.WaitHelper;
import com.AutomationPractice.testbase.TestBase;

public class SearchPage {
	private Logger log = LoggerHelper.getLogger(SearchPage.class);
	private WebDriver driver;
	WaitHelper waitHelper;
	
	@FindBy(id = "searchbox")
	WebElement searchBox;
	@FindBy(xpath = "//button[@name='submit_search']")
	WebElement searchIcon;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(searchBox, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("Navigation menu object created....");
	}

}
