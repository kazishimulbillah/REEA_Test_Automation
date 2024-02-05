package com.SC.Helper.Window;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.SC.Helper.Logger.LoggerHelper;

public class WindowHelper {
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
		log.info("WindowHelper object created");
	}

	/**
	 * This method will switch to parent window
	 */

	public void switchToParentWindow() {
		log.info("switching to parent window");
		driver.switchTo().defaultContent();
	}

}
