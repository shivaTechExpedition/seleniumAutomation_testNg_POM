package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.pages.base.BasePage;

public class AccountsPage extends BasePage{

	public AccountsPage(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(css = ".pageType")
	public WebElement accountTextEle;
	

	@FindBy(css  = "input[name='new']")
	public WebElement newEle;
	

	
	@FindBy(css=".title")
	public WebElement viewDropDown; 
	
	public void clickNewTab() {
		// NewAccountsPage newAP = new NewAccountsPage(driver);
		clickElement(newEle, "New tab");
//		WebDriverWait wait = addWait(3);
//		wait.until(ExpectedConditions.visibilityOf(newAP.accoutNameTextEle));
	}
		
	

}
