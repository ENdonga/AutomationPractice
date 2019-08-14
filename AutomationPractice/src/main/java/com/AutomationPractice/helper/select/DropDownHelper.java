package com.AutomationPractice.helper.select;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class DropDownHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropDownHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		log.info("Dropdown Helper object is created..... ");
	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("Select using value as: " + value);
		select.selectByValue(value);
	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("Select using index as: " + index);
		select.selectByIndex(index);
	}

	public void selectUsingVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		log.info("Select using text as: " + text);
		select.selectByVisibleText(text);
	}

	public void deselectUsingValue(WebElement element, String value) {
		Select deselect = new Select(element);
		log.info("Deselect using value as: " + value);
		deselect.deselectByValue(value);
	}

	public void deselectUsingIndex(WebElement element, int index) {
		Select deselect = new Select(element);
		log.info("Deselect using index as: " + index);
		deselect.deselectByIndex(index);
	}

	public void deselectUsingVisibleText(WebElement element, String text) {
		Select deselect = new Select(element);
		log.info("Deselect using text as: " + text);
		deselect.deselectByVisibleText(text);
	}

	public List<String> getAllDropDownData(WebElement element) {
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for (WebElement ele : elementList) {
			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}
	
	public String getSelectedOption(WebElement element) {
		Select select = new Select(element);
		WebElement option = select.getFirstSelectedOption();
		return option.getText();
	}

}
