package com.SC.QA.NewUserRegistration.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import com.SC.QA.Base.TestBase;
import com.SC.QA.Page.HomePage;
import com.SC.QA.Page.NewUserRegistartion;
import com.SC.QA.Page.ProductPurchase;
import com.SC.QA.Util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class ProductPurchaseTest extends TestBase {

    TestUtil testUtil;

    ProductPurchase productPurchase;
    String sheetName = "UserDetails";
    public ExtentReports extent;
    public ExtentTest extentTest;

    public ProductPurchaseTest() {
        super();
    }

    @BeforeTest
    public void setExtent() {
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
    }

    @AfterTest
    public void endReport() {
        extent.flush();
        extent.close();
    }

    @BeforeMethod
    public void setUp() throws InterruptedException {

        initialization();
        testUtil = new TestUtil();

        productPurchase = new ProductPurchase();
        productPurchase = PageFactory.initElements(driver, ProductPurchase.class);
    }

    @Test(priority = 3, dataProvider = "UserDetails")
    public void ProductsPurchaseTest(String name,String firstname, String lastname, String address, String country, String state, String city, String zipcode,String day, String month,
                                    String year, String mobileNumber, String cardNumber, String cvc, String expiryMonth, String expiryYear) throws Throwable {
        extentTest = extent.startTest("RegisterNewUserTest");
        productPurchase.ProductsPurchase(name, country, city, cardNumber, month, year);
    }

	@AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in
            // extent report

            String screenshotPath = ProductPurchaseTest.getScreenshot(driver, result.getName());
            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot in extent

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

        }

        extent.endTest(extentTest);

        driver.quit();

		CloseWebDriver();
    }

    @DataProvider
    public Object[][] UserDetails() {
        Object[][] data = TestUtil.getTestData(sheetName);

        return data;
    }
}
