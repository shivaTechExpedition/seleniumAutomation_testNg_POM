package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.pages.base.BasePage;
import com.salesforce.utility.Constants;
import com.salesforce.utility.PropertiesUtilClass;

public class ForgtoPasswordPage extends BasePage {

	public ForgtoPasswordPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(css = "input[id='un']")
	public WebElement userNameEle;
	
	@FindBy(xpath = "//input[@id='continue']")
	public WebElement continueBtn;
	
	@FindBy(xpath = "//div[@class= 'message']" )
	public WebElement passwordResetMessageEle;
	
	public void enterUserName() {
		enterText(userNameEle, PropertiesUtilClass.readDataFromPropertyFile(Constants.PATH, "username"), "username text field");
	}
	
	public void clickContinueBtn() {
	clickElement(continueBtn, "Continue");
	}
	
	public String getPasswordResetMessage() {
	return extractText(passwordResetMessageEle, "Password reset message");
	}
	
	
	

}
