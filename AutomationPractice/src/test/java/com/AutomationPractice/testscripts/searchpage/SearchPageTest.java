package com.AutomationPractice.testscripts.searchpage;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.AutomationPractice.helper.assertion.AssertionHelper;
import com.AutomationPractice.helper.assertion.VerificationHelper;
import com.AutomationPractice.helper.browserconfiguratiion.config.ObjectReader;
import com.AutomationPractice.helper.logger.LoggerHelper;
import com.AutomationPractice.pageobject.ApplicationText;
import com.AutomationPractice.pageobject.HomePage;
import com.AutomationPractice.pageobject.SearchPage;
import com.AutomationPractice.testbase.TestBase;

public class SearchPageTest extends TestBase{
	private final Logger log = LoggerHelper.getLogger(SearchPageTest.class);
	
	HomePage homePage;
	SearchPage searchPage;
	
	@Test(priority = 1)
	public void verifyProductPageTitleTest() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		searchPage = new SearchPage(driver);
		searchPage.performSearch(ApplicationText.Blouse);
		String title = searchPage.getPageTitle();
		String actual = new VerificationHelper(driver).getTitle();
		AssertionHelper.verifyText(actual, title);
		log.info("Test completed....... ");
		TestBase.logExtentReport("Test completed....... ");
	}
	
	@Test(priority = 2)
	public void testEnterSearchTextAndClickEnter() {
		getApplicationUrl(ObjectReader.reader.getUrl());
		searchPage = new SearchPage(driver);
		searchPage.enterTextInSearchAndClickEnter(ApplicationText.dress);
		AssertionHelper.verifyText(new VerificationHelper(driver).getElementText(searchPage.searchText), searchPage.getSearchedText());
		log.info("Test completed successfully.......");
		TestBase.logExtentReport("Test completed successfully.......");
	}

}
