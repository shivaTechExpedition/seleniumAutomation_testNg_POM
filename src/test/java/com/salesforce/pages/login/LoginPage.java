package com.salesforce.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.pages.base.BasePage;
import com.salesforce.pages.home.HomePage;
import com.salesforce.utility.Constants;
import com.salesforce.utility.PropertiesUtilClass;


public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(id="username")
	public WebElement userNameEle;
	
	@FindBy(id="password")
	public WebElement pswdEle;
	
	@FindBy(id="Login")
	public WebElement loginEle;
	
	
	
	@FindBy(id="error")
	public WebElement errorMessageEle;
	
	@FindBy(id="rememberUn")
	public WebElement rememberMeCheckBoxEle;
	
	@FindBy(id="idcard-identity")
	public WebElement userNameWithRememberedNameEle;
	
	@FindBy(id="forgot_password_link")
	public WebElement forgotPswdEle;
	
	@FindBy(xpath = "//div[@id='error']")
	public WebElement errorMsgEle;
	
	public void enterUserName(String username) {
		enterText(userNameEle, username, "username text field");
	}
	 
	public void enterPassword(String password) {
		enterText(pswdEle,password, "password text field");
	}
	
	public void clickLogin() {
		clickElement(loginEle, "Login");
	}
	
	public String getErrorMessage() {
		return extractText(errorMessageEle, "Error Message");
	}
	
	public void clickRememberMeCheckBox() {
		clickElement(rememberMeCheckBoxEle, "Remember me Checkbox");
	}
	
	public void clickForgotPswdEle() {
		clickElement(forgotPswdEle, "forgot your password? link");
	}
	
	public String extractErrorMessage() { 
	return extractText(errorMsgEle, "Error message text ");
	
	}
	
	// get the username from the .properties file
	public String getUserName(String filePath) {
		mylog.info("Fetch username from properties file");
		return PropertiesUtilClass.readDataFromPropertyFile(filePath, "username");
	}
	
	// get the password from the .properties file
	public String getPswd(String filePath) {
		mylog.info("Fetch password from properties file");
		return PropertiesUtilClass.readDataFromPropertyFile(filePath, "pswd");
	}
	
	
}
