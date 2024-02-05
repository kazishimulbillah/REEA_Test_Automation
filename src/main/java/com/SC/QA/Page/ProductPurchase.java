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

import java.util.List;

public class ProductPurchase extends TestBase {

    @FindBy(xpath = "//a[text()=\"Nexus 6\"]")
    WebElement Nexus6;

    @FindBy(xpath = "//a[text()=\"Add to cart\"]")
    WebElement AddToCart;

    @FindBy(xpath = "//a[text()=\"Cart\"]")
    WebElement CartMenu;

    @FindBy(xpath = "//button[text()=\"Place Order\"]")
    WebElement PlaceOrder;

    @FindBy(xpath = "//*[@id=\"name\"]")
    WebElement Name;

    @FindBy(xpath = "//*[@id=\"country\"]")
    WebElement Country;

    @FindBy(xpath = "//*[@id=\"city\"]")
    WebElement City;

    @FindBy(xpath = "//*[@id=\"card\"]")
    WebElement CreditCard;

    @FindBy(xpath = "//*[@id=\"month\"]")
    WebElement Month;

    @FindBy(xpath = "//*[@id=\"year\"]")
    WebElement Year;

    @FindBy(xpath = "//button[text()=\"Purchase\"]")
    WebElement PurchaseButton;

    @FindBy(xpath = "//*[@id=\"itemc\"]")
    List<WebElement> ItemCategory;

    JavaScriptHelper javaScript = new JavaScriptHelper(driver);
    DropDownHelper dropDown = new DropDownHelper(driver);
    ActionHelper action = new ActionHelper(driver);
    WaitHelper wait = new WaitHelper(driver);
    AlertHelper alert = new AlertHelper(driver);

    public ProductPurchase() {
        PageFactory.initElements(driver, this);
    }

    public void ProductsPurchase(String name, String country, String city, String creditCardNum, String month, String year) throws Exception {

        Thread.sleep(1000);
        javaScript.clickElement(ItemCategory.get(0));
        System.out.println(ItemCategory.get(0).getText());

        Thread.sleep(1000);
        javaScript.clickElement(Nexus6);

        Thread.sleep(1000);
        javaScript.clickElement(AddToCart);

        Thread.sleep(1000);
        alert.acceptAlert();

        Thread.sleep(1000);
        javaScript.clickElement(CartMenu);

        Thread.sleep(2000);
        javaScript.clickElement(PlaceOrder);

        Thread.sleep(2000);
        Name.sendKeys(name);

        Thread.sleep(1000);
        Country.sendKeys(country);

        Thread.sleep(1000);
        City.sendKeys(city);

        Thread.sleep(1000);
        CreditCard.sendKeys(creditCardNum);

        Thread.sleep(1000);
        Month.sendKeys(month);

        Thread.sleep(1000);
        Year.sendKeys(year);

        Thread.sleep(2000);
        javaScript.clickElement(PurchaseButton);
    }
}
