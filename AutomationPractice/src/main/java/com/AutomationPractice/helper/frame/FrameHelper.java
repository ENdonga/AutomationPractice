package com.AutomationPractice.helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class FrameHelper {

	private Logger log = LoggerHelper.getLogger(FrameHelper.class);
	private WebDriver driver;

	public FrameHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method switches to a frame based on the frame index
	 * 
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("Switched to: " + frameIndex + " frame");
	}

	/**
	 * This method switches to a frame based on the frame name
	 * 
	 * @param frameName
	 */
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("Switched to: " + frameName + " frame");
	}

	/**
	 * This method switches to a frame based on the frame web element
	 * 
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("Switched to frame" + element.toString());
	}
}
