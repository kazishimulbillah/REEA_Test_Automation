package com.SC.Helper.Frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.SC.Helper.Logger.LoggerHelper;
import com.SC.QA.Base.TestBase;

public class FrameHelper extends TestBase {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(FrameHelper.class);

	public FrameHelper(WebDriver driver) {
		this.driver = driver;
		log.info("FrameHelper object is created");
	}

	/**
	 * switchToFrame based on index
	 * 
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
		log.info("switched to :" + index + " frame ");
	}

	/**
	 * switchToFrame based on frameName
	 * 
	 * @param index
	 */
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to :" + frameName + " frame ");
	}

	/**
	 * switchToFrame based on frame webelement
	 * 
	 * @param index
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("switched to frame :" + element.toString());
	}
}
