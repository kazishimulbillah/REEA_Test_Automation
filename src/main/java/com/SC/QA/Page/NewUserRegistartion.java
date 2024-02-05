package com.SC.QA.Page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.SC.Helper.Action.ActionHelper;
import com.SC.Helper.Alert.AlertHelper;
import com.SC.Helper.JavaScript.JavaScriptHelper;
import com.SC.Helper.Select.DropDownHelper;
import com.SC.Helper.Wait.WaitHelper;
import com.SC.QA.Base.TestBase;

public class NewUserRegistartion extends TestBase {

	@FindBy(xpath = "//*[@id=\"signin2\"]")
	WebElement SignupMenu;

	@FindBy(xpath = "//*[@id=\"sign-username\"]")
	WebElement Username;

	@FindBy(xpath = "//*[@id=\"sign-password\"]")
	WebElement Password;

	@FindBy(xpath = "//button[text()=\"Sign up\"]")
	WebElement SignupButton;

	@FindBy(xpath = "//*[@id=\"login2\"]")
	WebElement LoginMenu;

	@FindBy(xpath = "//*[@id=\"loginusername\"]")
	WebElement LoginUsername;

	@FindBy(xpath = "//*[@id=\"loginpassword\"]")
	WebElement LoginPassword;

	@FindBy(xpath = "//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")
	WebElement LoginButton;

	@FindBy(xpath = "//*[@id=\"nava\"]")
	WebElement PageTitle;

	JavaScriptHelper javaScript = new JavaScriptHelper(driver);
	DropDownHelper dropDown = new DropDownHelper(driver);
	ActionHelper action = new ActionHelper(driver);
	WaitHelper wait = new WaitHelper(driver);
	AlertHelper alert = new AlertHelper(driver);

	public NewUserRegistartion() {
		PageFactory.initElements(driver, this);
	}



	public void RegisterANewUser(String email, String password) throws Exception {

		Thread.sleep(1000);
		javaScript.clickElement(SignupMenu);

		Thread.sleep(1000);
		Username.sendKeys(email);

		Thread.sleep(1000);
		Password.sendKeys(password);

		Thread.sleep(1000);
		javaScript.clickElement(SignupButton);

		Thread.sleep(1000);
		alert.acceptAlert();

		Thread.sleep(1000);
		javaScript.clickElement(LoginMenu);

		Thread.sleep(1000);
		LoginUsername.sendKeys(email);

		Thread.sleep(1000);
		LoginPassword.sendKeys(password);

		Thread.sleep(1000);
		javaScript.clickElement(LoginButton);

		Thread.sleep(2000);
		Assert.assertEquals(PageTitle.getText(), "PRODUCT STORE");
		System.out.println(PageTitle.getText());
	}
}
