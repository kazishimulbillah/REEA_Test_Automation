package com.SC.QA.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.SC.QA.Base.TestBase;
import com.SC.QA.ConfigFileReader.FileReaderManager;

public class TestUtil extends TestBase {

	@FindBy(xpath = "//A[@id='hrefCPV']/self::A")
	WebElement SelectCategory;

	@FindBy(className = "jstree-checkbox")
	WebElement CpvCatAgri;

	@FindBy(id = "btnGetCheckedNode")
	WebElement SubmitCpvCategory;

	@FindBy(xpath = "//*[@id=\"7335\"]/a/ins[1]")
	WebElement computerRel;

	static Workbook book;
	static Sheet sheet;

	public static long PAGE_LOAD_TIMEOUT = 1000;
	public static long IMPLICIT_WAIT = 100;

	public void randomNo() {
		WebElement WebEle = driver.findElement(By.id("txtPackageNo"));

		java.util.Random rand = new java.util.Random();
		int n = rand.nextInt(999999999) + 1;
		String PackageNo_1 = Integer.toString(n);
		WebEle.sendKeys(PackageNo_1);
	}

	public void randomNoApp() {
		WebElement WebEle = driver.findElement(By.id("txtAppCode"));

		java.util.Random rand = new java.util.Random();
		int n = rand.nextInt(99999999) + 1;
		String PackageNo_1 = Integer.toString(n);
		WebEle.sendKeys(PackageNo_1);

	}

	public void switchToFrame() {
		driver.switchTo().frame("");
	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(FileReaderManager.getInstance().getConfigReader().getTestDataPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				Cell cell1 = sheet.getRow(i + 1).getCell(k);
				cell1.setCellType(Cell.CELL_TYPE_STRING);
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				data[i][k] = cell1.getStringCellValue();
				System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String currentDir = System.getProperty("C:\\AutomationConfig");
		String currentDir = System.getProperty("user.dir");

		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

	}

}
