package com.SC.Helper.Select;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.SC.Helper.Logger.LoggerHelper;
import com.SC.Helper.Wait.WaitHelper;

public class DropDownHelper {

	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(DropDownHelper.class);

	WaitHelper waithelper;

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		log.info("DropDownHelper object created");
	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("selectUsingValue and value is " + value);
		select.selectByValue(value);
	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("selectUsingIndex and index is " + index);
		select.selectByIndex(index);
	}

	public void selectUsingVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		log.info("selectUsingVisibleText and visibletext is " + text);
		select.selectByVisibleText(text);
	}
}
