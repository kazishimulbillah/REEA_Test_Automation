package com.SC.QA.Page;

import com.SC.Helper.Action.ActionHelper;
import com.SC.Helper.Alert.AlertHelper;
import com.SC.Helper.JavaScript.JavaScriptHelper;
import com.SC.Helper.Select.DropDownHelper;
import com.SC.Helper.Wait.WaitHelper;
import com.SC.QA.Base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage extends TestBase {

    @FindBy(xpath = "//*[@id=\"nava\"]")
    WebElement PageTitle;

    @FindBy(xpath = "//*[@id=\"itemc\"]")
    List<WebElement> ItemCategory;// *[@id="tbodyid"]/div[1]

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div")
    List<WebElement> ItemNumber;

    JavaScriptHelper javaScript = new JavaScriptHelper(driver);
    DropDownHelper dropDown = new DropDownHelper(driver);
    ActionHelper action = new ActionHelper(driver);
    WaitHelper wait = new WaitHelper(driver);
    AlertHelper alert = new AlertHelper(driver);

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void ItemCount(String numberOfPhones, String numberOfLaptops, String numberOfMonitors)
            throws InterruptedException {

        Thread.sleep(500);
        Assert.assertEquals(driver.getTitle(), "STORE");

        Thread.sleep(500);
        Assert.assertEquals(PageTitle.getText(), "PRODUCT STORE");

        System.out.println(PageTitle.getText());

        Thread.sleep(1000);
        javaScript.clickElement(ItemCategory.get(0));
        System.out.println(ItemCategory.get(0).getText());

        Thread.sleep(1000);
        int NumberOfPhones = ItemNumber.size();
        System.out.println(NumberOfPhones);

        Thread.sleep(1000);
        Assert.assertEquals(NumberOfPhones, Integer.parseInt(numberOfPhones));

        Thread.sleep(1000);
        javaScript.clickElement(ItemCategory.get(1));
        System.out.println(ItemCategory.get(1).getText());

        Thread.sleep(1000);
        int NumberOfLaptops = ItemNumber.size();
        System.out.println(NumberOfLaptops);

        Thread.sleep(1000);
        Assert.assertEquals(NumberOfLaptops, Integer.parseInt(numberOfLaptops));

        Thread.sleep(1000);
        javaScript.clickElement(ItemCategory.get(2));
        System.out.println(ItemCategory.get(2).getText());

        Thread.sleep(1000);
        int NumberOfMonitors = ItemNumber.size();
        System.out.println(NumberOfMonitors);

        Thread.sleep(1000);
        Assert.assertEquals(NumberOfMonitors, Integer.parseInt(numberOfMonitors));
    }
}
