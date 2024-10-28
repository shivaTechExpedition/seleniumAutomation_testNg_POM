package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.pages.base.BasePage;

public class AllTabsPage extends BasePage{

	public AllTabsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h1[contains(text(),'All Tabs')]")
	public WebElement AllTabsTextEle;
	
	@FindBy(linkText = "Accounts")
	public WebElement Accounts;
	
	public void clickLink(WebElement linkName) {
		mouseOverElement(linkName, linkName.getText());
		clickElement(linkName, linkName.getText());
	}

}
