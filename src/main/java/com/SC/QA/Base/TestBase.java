package com.SC.QA.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SC.Helper.JavaScript.JavaScriptHelper;
import com.SC.QA.ConfigFileReader.FileReaderManager;
import com.SC.QA.Util.TestUtil;
import com.SC.QA.Util.WebEventListener;

public class TestBase {

	@FindBy(className = "DynarchCalendar-day")
	private static List<WebElement> Days;

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static int n = 0;

	static Workbook book;
	static Sheet sheet;

	public static void selectDateByJs(WebDriver driver, WebElement element, String dateVal) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value','" + dateVal + "');", element);
	}

	public void clickOn(WebDriver driver, WebElement locator, int timeout) {

		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));

		JavaScriptHelper javaScript = new JavaScriptHelper(driver);
		javaScript.clickElement(locator);
	}

	public static void clickOn1(WebDriver driver, WebElement locator, int timeout) {

		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));

		locator.click();
	}

	public static void moveOn(WebDriver driver, WebElement locator, int timeout) {

		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(locator));
	}

	public static void moveOn1(WebDriver driver, WebElement locator, int timeout) {

		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";

		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static void clickOn2(WebDriver driver, WebElement locator, int timeout) {

		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

		locator.click();
	}

	public TestBase() {
		try {
			prop = new Properties();

			FileInputStream ip = new FileInputStream(
					"src/main/resources/Configuation.properties");
			prop = new Properties(System.getProperties());
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					FileReaderManager.getInstance().getConfigReader().getDriverPath());

			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					FileReaderManager.getInstance().getConfigReader().getDriverPath());
			driver = new FirefoxDriver();
		}

		else if (browserName.equals("ME")) {
			System.setProperty("webdriver.ie.driver",
					FileReaderManager.getInstance().getConfigReader().getDriverPath());
			driver = new InternetExplorerDriver();
		}

		e_driver = new EventFiringWebDriver(driver);

		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	}

	public static void CloseWebDriver() {

		try {
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		} catch (IOException e) {
			System.out.println("ChromeDriver Memory is not cleared");
		}
	}
}