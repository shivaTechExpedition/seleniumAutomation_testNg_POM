package com.salesforce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.salesforce.pages.base.BasePage;

public class CreateNewViewPage extends BasePage{

	public CreateNewViewPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(linkText = "Create New View")
	public WebElement createNewViewLink;
	
	@FindBy(id="fname")
	WebElement viewNameEle;
	
	@FindBy(id="devname")
	public WebElement uniqueViewNameEle;
	
	@FindBy(name= "save")
	public WebElement saveBtn; 
	

	
	public void clickCreateNewView() {
	clickElement(createNewViewLink, "Create New View Link");
	}
	
	public void enterViewName(String accountName) {
	enterText(viewNameEle, accountName, "View Name TextField");
	}
	
	public void enterUniqueView() {
	enterText(uniqueViewNameEle, "", "Unique View Name TextField");
	}
	
	
	public void clickSave() {
	clickElement(saveBtn, "Save");
	}
	
	
	
	

}
