package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.pages.base.BasePage;

public class NewAccountsPage extends BasePage{

	public NewAccountsPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(css = ".pageDescription")
	public WebElement newAccountTextEle;
	
	@FindBy(id= "acc2")
	public WebElement accoutNameTextEle;
	
	@FindBy(id = "acc6")
	public WebElement typeDropDown; 
	
	@FindBy(id = "00Nbm000004Ld2U")
	public WebElement customerPriority;
	
	@FindBy(name = "save")
	public WebElement saveBtn;
	
	
		
	public void enterAccountName(String accountName) {
		enterText(accoutNameTextEle,accountName,"Account Name");
	}
	
	public void clickSaveBtn() {
		clickElement(saveBtn, "Save");
	}
}
