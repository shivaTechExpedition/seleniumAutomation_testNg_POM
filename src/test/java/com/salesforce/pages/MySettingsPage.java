package com.salesforce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.salesforce.pages.base.BasePage;



public class MySettingsPage extends BasePage {
	
	public MySettingsPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(id = "PersonalInfo_font")
	public WebElement personalEle;
	
	@FindBy(id = "LoginHistory_font")
	public WebElement loginHistoryEle; 
	
	@FindBy(id="DisplayAndLayout")
	public WebElement displayAndLayOutEle;

	@FindBy(css="div[id=DisplayAndLayout_child] div")
	public List<WebElement> customMyTabsElems;
	
	@FindBy(css="#duel_select_0")
	public WebElement availableTabsList;
	
	@FindBy(css="#duel_select_1")
	public WebElement selectedTabs;
	
	@FindBy(css=".rightArrowIcon")
	public WebElement addBtn;
	
	@FindBy(css=".leftArrowIcon")
	public WebElement removeBtn;
	
	@FindBy(css="#p4")
	public WebElement customAppDDList;
	
	// Click on personal link and select Login History link.
	public void clickPersonalTab() {
		clickElement(personalEle, "Personal Tab");
	}
	
	
	
	public void mouseOvertoLoginHistoryOptionAndClick() {
		mouseOverElement(loginHistoryEle, "Login History Option");
		clickElement(loginHistoryEle, "Login History Option");
	}

			
	// Click on Display & Layout link
	public void clickDisplayAndLayoutTab() {
		clickElement(displayAndLayOutEle, "Display and Layout tab");
	}

	// select option from customizeMyTabs dropdown Customize My Tabs
	public void selectDisplayAndLayOutOption(String option ) {
		dropDownElementSelection(customMyTabsElems, option, option);
	}
	
	public void selectAvailableTabsOption(String option) {
		selectByVisibleText(availableTabsList, option, option);
	}
	
	public void deselectSelectedTabsOption(String option) {
		selectByVisibleText(selectedTabs, option, option);
		clickRemoveBtn();
	}
	
	public void clickAddBtn() {
		clickElement(addBtn, "Add");
	}
	
	public void clickRemoveBtn() {
		clickElement(removeBtn, "Remove");
	}
	
			

}
